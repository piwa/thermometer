package at.piwa.thermometer.sensorservice.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SensorDBServices {

    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private PagingSensorRepository pagingSensorRepository;

    public List<Sensor> findAll() {
        Iterable<Sensor> temperatureDTOs = sensorRepository.findAll();
        return StreamSupport.stream(temperatureDTOs.spliterator(), false).collect(Collectors.toList());
    }

    public Page<Sensor> findAll(Pageable pageable) {
        return pagingSensorRepository.findAll(pageable);
    }

    public Sensor findOne(String id) {
        return pagingSensorRepository.findById(id).orElse(null);
    }

    public Sensor registerSensor(Sensor sensor) {
        if(StringUtils.isEmpty(sensor.getId())) {
            sensor.setId(UUID.randomUUID().toString());
        }
        return sensorRepository.findById(sensor.getId()).orElseGet(() -> sensorRepository.save(sensor));
    }
}
