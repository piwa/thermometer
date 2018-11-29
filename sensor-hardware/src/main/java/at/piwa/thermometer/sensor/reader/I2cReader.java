package at.piwa.thermometer.sensor.reader;

import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.Temperature;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by philippwaibel on 19/06/16.
 */
@Component
@Slf4j
public class I2cReader implements TemperatureReader {

    private I2CBus bus;
    private I2CDevice device;

    private static final byte START_CONVERT_CMD = (byte) 0xEE;
    private static final byte READ_TEMP_CMD = (byte) 0xAA;
    private static final byte COUNT_PER_C_CMD = (byte) 0xA9;
    private static final byte COUNT_REMAIN_CMD = (byte) 0xA8;

    public I2cReader() {
    }

    public Temperature readTemperature(Sensor sensor) {
        Temperature temp = new Temperature();

        log.debug("Read I2C sensor: " + sensor);
        try {

            byte[] readBuf = new byte[2];
            device.read(0xAA, readBuf, 0, 2);

            log.debug("Bytes received from I2C: "+ Arrays.toString(readBuf));

            double temperatureValue = convertTemperature(readBuf);

            temp = new Temperature();
            temp.setTime(DateTime.now());
            temp.setTemperature(temperatureValue);
            temp.setSensor(sensor);

        } catch (IOException e) {
            log.error("Exception while reading I2C temperature", e);
        }

        log.debug("Read I2C sensor done: " + sensor);
        return temp;
    }

    private double convertTemperature(byte[] readBuf) {
        int temperatureValue_h = readBuf[0];
        int temperatureValue_l = readBuf[1] >> 4;

        double decimalPlace = 0.0625 * Math.abs(temperatureValue_l);
        if(temperatureValue_l < 0) {
            decimalPlace = 1 - decimalPlace;
        }

        return temperatureValue_h + decimalPlace;
    }

    public void init(Sensor sensor) throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {
        // create I2C communications bus instance
        log.debug("Open I2C connection to sensor: " + sensor);
        bus = I2CFactory.getInstance(I2CBus.BUS_1);

        // create I2C device instance
        log.debug("Create I2C device instance: " + bus.toString());
        device = bus.getDevice(Integer.decode(sensor.getHardwareID()));

        // Start Conversion
        log.debug("Start I2C conversation: " + bus.toString());
        device.write(START_CONVERT_CMD);
        Thread.sleep(1000);
        log.debug("I2C connection to sensor open: " + sensor);
    }
}
