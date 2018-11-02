package at.piwa.thermometer.temperatureservice.database;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Temperature {

    private String temperatureID;

    private DateTime time;

    private String sensorId;

    private Double temperature;


    public Temperature(TemperatureDto dto) {
        this.temperatureID = dto.getTemperatureID();
        this.time = dto.getPayload().getTime();
        this.sensorId = dto.getPayload().getSensorId();
        this.temperature = dto.getPayload().getTemperature();
    }
}


