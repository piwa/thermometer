package at.piwa.thermometer.sensor.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.UUID;

/**
 * Created by philippwaibel on 17/01/16.
 */
@Data
public class Temperature {

    private final String temperatureID = UUID.randomUUID().toString();

    private DateTime time;

    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    private Sensor sensor;

    private double temperature;



}
