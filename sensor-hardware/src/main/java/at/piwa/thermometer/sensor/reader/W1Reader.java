package at.piwa.thermometer.sensor.reader;

import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.Temperature;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

/**
 * Created by philippwaibel on 19/06/16.
 */
@Component
@Slf4j
public class W1Reader implements TemperatureReader {

    /* path to search for devices in filesystem */
    private static String devicesPath = "/sys/bus/w1/devices";

    /* file of the measured values */
    private static String valueFile = "w1_slave";

    @Value("${thermometer.simulation}")
    private boolean simulation;


    public Temperature readTemperature(Sensor sensor) {

        log.debug("Read Wire-1 sensor: " + sensor);

        Temperature temp = new Temperature();
        temp.setTime(DateTime.now());
        temp.setSensor(sensor);

        if(!simulation) {
            Path path = FileSystems.getDefault().getPath(devicesPath, sensor.getHardwareID(), valueFile);
            List<String> lines;

            int attempts = 3;
            boolean crcOK = false;

            while (attempts > 0) {
                try {
                    lines = Files.readAllLines(path);
                    for (String line : lines) {
                        if (line.endsWith("YES")) {
                            crcOK = true;
                        } else if (line.matches(".*t=[0-9]+") && crcOK) {
                            double value = Integer.valueOf(line.substring(line.indexOf("=") + 1)) / 1000.0;
                            temp.setTemperature(value);
                            log.info("Read temperature: " + temp);
                            return temp;
                        }
                    }
                } catch (Exception e) {
                    log.error("Exception", e);
                }
                attempts--;
            }

            temp.setTemperature(Double.MAX_VALUE);
        }
        else {
            temp = SimulationUtilities.createSimulationTemperature(sensor);
        }

        log.debug("Read Wire-1 sensor done: " + sensor);
        return temp;
    }
}
