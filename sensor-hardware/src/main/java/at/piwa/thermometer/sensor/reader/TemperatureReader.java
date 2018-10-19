package at.piwa.thermometer.sensor.reader;

import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.Temperature;

/**
 * Created by philippwaibel on 19/06/16.
 */
public interface TemperatureReader {
    Temperature readTemperature(Sensor sensor);
}
