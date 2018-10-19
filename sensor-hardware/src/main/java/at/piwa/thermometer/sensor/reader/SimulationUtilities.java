package at.piwa.thermometer.sensor.reader;

import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.Temperature;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Random;

@Slf4j
public class SimulationUtilities {

    public static Temperature createSimulationTemperature(Sensor sensor) {
        int min = 20;
        int max = 26;


        Temperature temp = new Temperature();

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        temp.setTime(DateTime.now());
        temp.setTemperature(randomNum);
        temp.setSensor(sensor);

        log.info("Simulation: Sensor: " + sensor.toString() + "; Temperature: " + temp);

        return temp;
    }

}
