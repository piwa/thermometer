package at.piwa.thermometer.sensor;

import at.piwa.thermometer.sensor.domain.Sensor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class InMemoryCache {

    private List<Sensor> sensors = new ArrayList<>();

    public void addAllSensors(List<Sensor> sensors) {
        sensors.addAll(sensors);
    }
}
