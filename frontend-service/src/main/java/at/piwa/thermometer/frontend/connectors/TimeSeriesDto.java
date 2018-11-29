package at.piwa.thermometer.frontend.connectors;

import lombok.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSeriesDto {

    private DateTime time;
    private List<TemperatureDto> temperatureList = new ArrayList<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemperatureDto {
        private String sensorId;
        private Double temperature;
    }

}
