package at.piwa.thermometer.temperatureservice.rest;

import lombok.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Data
public class TimeSeriesDto {

    private DateTime time;
    private List<TemperatureDto> temperatureList = new ArrayList<>();

    @Data
    @AllArgsConstructor
    public static class TemperatureDto {
        private String sensorId;
        private Double temperature;
    }

}
