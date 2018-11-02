package at.piwa.thermometer.temperatureservice.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TemperatureServices {

    @Autowired
    private TemperatureRepository temperatureRepository;
    @Autowired
    private PagingTemperatureRepository pagingTemperatureRepository;

    public List<Temperature> findAll() {
        Iterable<TemperatureDto> temperatureDTOs = temperatureRepository.findAll();
        return StreamSupport.stream(temperatureDTOs.spliterator(), false).map(dto -> new Temperature(dto)).collect(Collectors.toList());
    }

    public Page<Temperature> findAll(Pageable pageable) {
        Page<TemperatureDto> temperatureDTOs = pagingTemperatureRepository.findAll(pageable);
        List<Temperature> elements = StreamSupport.stream(temperatureDTOs.spliterator(), false).map(dto -> new Temperature(dto)).collect(Collectors.toList());
        return new PageImpl<>(elements, temperatureDTOs.getPageable(), temperatureDTOs.getTotalElements());
    }

}
