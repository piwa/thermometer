package at.piwa.thermometer.frontend.connectors;

import lombok.Data;

/**
 * Created by philippwaibel on 18/01/16.
 */
@Data
public class SensorDto {

    private String id;
    private String name;
    private String type;
    private String description;
    private SensorConnection sensorConnection;
    private String hardwareID;

}
