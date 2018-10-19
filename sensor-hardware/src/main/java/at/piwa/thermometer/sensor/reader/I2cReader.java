package at.piwa.thermometer.sensor.reader;

import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.Temperature;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by philippwaibel on 19/06/16.
 */
@Component
@Slf4j
public class I2cReader implements TemperatureReader {

    private I2CBus bus;
    private I2CDevice device;

    @Value("${thermometer.simulation}")
    private boolean simulation;

    private static final byte START_CONVERT_CMD  = (byte)0xEE;
    private static final byte READ_TEMP_CMD      = (byte)0xAA;
    private static final byte COUNT_PER_C_CMD    = (byte)0xA9;
    private static final byte COUNT_REMAIN_CMD   = (byte)0xA8;

    public I2cReader() {
    }

    public Temperature readTemperature(Sensor sensor) {
        Temperature temp = new Temperature();

        if(!simulation) {
            try {

                init(sensor);

                device.write(READ_TEMP_CMD);

                byte[] readBuf = new byte[2];
                device.read(readBuf, 0, 2);

//                double temperatureValue = Double.valueOf(readBuf[0]).doubleValue();

//                if (readBuf[1] != 0) {
//                    temperatureValue = temperatureValue + 0.5;
//                }

                byte[] results = new byte[2];
                int bytesRead = device.read(results, 0, 2);
                if (bytesRead == 2) {
                    double temperatureValue = 0;
                    int temperature = 0x7F & results[0];
                    int tempSign = 0x80 & results[0];
                    if (tempSign != 0) {
                        temperature = temperature - 128;
                    }
                    if (results[1] != 0) {
                        temperatureValue = temperature + 0.5;
                    }
                    if (temperatureValue <= -55) {
                        // TODO redoo?
                    }

                    temp = new Temperature();
                    temp.setTime(DateTime.now());
                    temp.setTemperature(temperatureValue);
                    temp.setSensor(sensor);
                    log.info("Sensor: " + sensor.toString() + "; Temperature: " + temp);
                }

            } catch (IOException e) {
                log.error("Exception", e);
            }
            finally {
                if(bus != null) {
                    try {
                        bus.close();
                    } catch (IOException e) {
                        log.error("Exception", e);
                    }
                }
            }

        }

        else {
            temp = SimulationUtilities.createSimulationTemperature(sensor);
        }

        return temp;
    }


    public void init(Sensor sensor) {
        try {
            // create I2C communications bus instance
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
            log.debug(bus.toString());

            // create I2C device instance
            device = bus.getDevice(Integer.valueOf(sensor.getHardwareID()));

            // Start Conversion
            device.write(START_CONVERT_CMD);
            Thread.sleep(100);

        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}
