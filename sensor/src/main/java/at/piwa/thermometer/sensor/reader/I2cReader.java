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
import java.util.Random;

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

    public I2cReader() {
    }

    public Temperature readTemperature(Sensor sensor) {
        Temperature temp = new Temperature();

        if(!simulation) {
            try {

                init();

                byte[] readBuf = new byte[2];
                device.read(Integer.valueOf(sensor.getHardwareID()), readBuf, 0, 2);

                double temperatureValue = Double.valueOf(readBuf[0]).doubleValue();

                if (readBuf[1] != 0) {
                    temperatureValue = temperatureValue + 0.5;
                }

                temp = new Temperature();
                temp.setTime(DateTime.now());
                temp.setTemperature(temperatureValue);
                temp.setSensor(sensor);
                log.info(DateTime.now().toString() + ": Read Temperature: " + temperatureValue);
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

    @Override
    public void init() {
        try {
            // create I2C communications bus instance
            bus = I2CFactory.getInstance(I2CBus.BUS_1);
            log.debug(bus.toString());

            // create I2C device instance
            device = bus.getDevice(0x48);

            // Start Conversion
            device.write((byte) 0xEE);
            Thread.sleep(100);

        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}
