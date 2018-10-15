package at.piwa.thermometer.sensor.domain;


import lombok.Data;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * Created by philippwaibel on 17/01/16.
 */
@Data
public class Temperature {

    private final String TemperatureID = UUID.randomUUID().toString();

    private DateTime time;

    private Sensor sensor;

    private double temperature;



}
