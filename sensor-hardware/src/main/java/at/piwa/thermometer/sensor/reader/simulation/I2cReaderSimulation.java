package at.piwa.thermometer.sensor.reader.simulation;

import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.Temperature;
import at.piwa.thermometer.sensor.reader.TemperatureReader;
import com.pi4j.io.i2c.I2CFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by philippwaibel on 19/06/16.
 */
@Component
@Slf4j
@Profile("dev-simulation")
public class I2cReaderSimulation implements TemperatureReader {

    public I2cReaderSimulation() {
    }

    public Temperature readTemperature(Sensor sensor) {
        log.debug("Read I2C sensor: " + sensor);
        Temperature temp = SimulationUtilities.createSimulationTemperature(sensor);
        log.debug("Read I2C sensor done: " + sensor);
        return temp;
    }


    public void init(Sensor sensor) throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException {

    }
}
