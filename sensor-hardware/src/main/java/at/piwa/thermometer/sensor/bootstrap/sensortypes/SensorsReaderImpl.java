package at.piwa.thermometer.sensor.bootstrap.sensortypes;

import at.piwa.thermometer.sensor.domain.Sensor;
import com.sun.javafx.scene.shape.PathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by philippwaibel on 18/10/2016.
 */
@Component
@Slf4j
public class SensorsReaderImpl {

    @Value("${sensor.configurations.path}")
    private String sensorConfigurationsPath;
    @Value("${sensor.configurations.original.path}")
    private String sensorConfigurationsOriginalPath;

    public List<Sensor> readSensorConfigurations() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( SensorConfigurations.class );
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File file = Paths.get(this.getClass().getClassLoader().getResource(sensorConfigurationsPath).toURI()).toFile();
            if(!file.exists()) {
                file = Paths.get(this.getClass().getClassLoader().getResource(sensorConfigurationsOriginalPath).toURI()).toFile();
            }

            SensorConfigurations sensorConfigurations = (SensorConfigurations) jaxbUnmarshaller.unmarshal(file);

            return sensorConfigurations.getSensors();
        } catch (JAXBException | URISyntaxException e) {
            log.error("Exception", e);
        }

        return null;
    }
}
