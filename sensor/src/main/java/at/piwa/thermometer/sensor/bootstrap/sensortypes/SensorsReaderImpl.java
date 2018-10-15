package at.piwa.thermometer.sensor.bootstrap.sensortypes;

import at.piwa.thermometer.sensor.InMemoryCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Created by philippwaibel on 18/10/2016.
 */
@Component
@Slf4j
public class SensorsReaderImpl {

    @Autowired
    private InMemoryCache inMemoryCache;

    @Value("${sensor.configurations.path}")
    private String sensorConfigurationsPath;

    public void readSensorConfigurations() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( SensorConfigurations.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File file = Paths.get(this.getClass().getClassLoader().getResource(sensorConfigurationsPath).toURI()).toFile();
            SensorConfigurations sensorConfigurations = (SensorConfigurations) jaxbUnmarshaller.unmarshal(file);
            inMemoryCache.addAllSensors(sensorConfigurations.getSensors());
        } catch (JAXBException | URISyntaxException e) {
            log.error("Exception", e);
        }

    }
}
