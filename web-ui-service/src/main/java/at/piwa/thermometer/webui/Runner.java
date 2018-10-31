package at.piwa.thermometer.webui;

import at.piwa.thermometer.webui.database.*;
import org.springframework.data.domain.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@Slf4j
public class Runner implements CommandLineRunner {

    @Autowired
    private TemperatureServices temperatureServices;

    @Override
    public void run(String... args) throws Exception {

        try {

            List<Temperature> temperatures = temperatureServices.findAll();

            temperatures.sort(Comparator.comparing(Temperature::getTime));

//            Page<Temperature> pagingTemperatures = temperatureServices.findAll(PageRequest.of(0,2));

            temperatures.forEach(temperatureDto -> log.info(temperatureDto.toString()));
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}
