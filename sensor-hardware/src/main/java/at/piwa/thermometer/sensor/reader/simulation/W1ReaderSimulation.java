package at.piwa.thermometer.sensor.reader.simulation;

import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.Temperature;
import at.piwa.thermometer.sensor.reader.TemperatureReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by philippwaibel on 19/06/16.
 */
@Component
@Slf4j
@Profile("dev-simulation")
public class W1ReaderSimulation implements TemperatureReader {

    public Temperature readTemperature(Sensor sensor) {
        log.debug("Read Wire-1 sensor: " + sensor);
        Temperature temp = SimulationUtilities.createSimulationTemperature(sensor);
        log.debug("Read Wire-1 sensor done: " + sensor);
        return temp;
    }
}
