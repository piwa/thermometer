package at.piwa.thermometer.sensor.bootstrap.sensortypes;

import at.piwa.thermometer.sensor.domain.Sensor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
public class SensorsWriteImpl {

    @Value("${sensor.configurations.path}")
    private String sensorConfigurationsPath;

    public void writeSensorConfigurations(SensorConfigurations sensorConfigurations) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance( SensorConfigurations.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            File file = Paths.get(this.getClass().getClassLoader().getResource(sensorConfigurationsPath).toURI()).toFile();
            jaxbMarshaller.marshal(sensorConfigurations, file);

        } catch (JAXBException | URISyntaxException e) {
            log.error("Exception", e);
        }
    }
}
