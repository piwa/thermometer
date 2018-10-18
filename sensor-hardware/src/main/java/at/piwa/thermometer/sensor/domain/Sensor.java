package at.piwa.thermometer.sensor.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

/**
 * Created by philippwaibel on 18/01/16.
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Sensor {

    @XmlElement
    private String Id;

    @XmlElement(name = "Name")
    private final String name;

    @XmlElement(name = "Type")
    private final String type;

    @XmlElement(name = "Description")
    private final String description;

    @XmlElement(name = "SensorConnection")
    private final SensorConnection sensorConnection;

    @XmlElement(name = "HardwareID")
    private final String hardwareID;

}
