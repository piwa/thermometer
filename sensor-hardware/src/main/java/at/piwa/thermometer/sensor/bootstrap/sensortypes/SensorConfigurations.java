package at.piwa.thermometer.sensor.bootstrap.sensortypes;

import at.piwa.thermometer.sensor.domain.Sensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by philippwaibel on 18/10/2016.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="SensorConfigurations")
@XmlAccessorType(XmlAccessType.FIELD)
public class SensorConfigurations {

    @XmlElement(name = "Sensor")
    private List<Sensor> sensors = new ArrayList<>();

}
