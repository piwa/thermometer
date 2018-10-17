package at.piwa.thermometer.sensor.bootstrap;

import at.piwa.thermometer.sensor.bootstrap.sensortypes.SensorsReaderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by philippwaibel on 17/01/16.
 */
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SensorsReaderImpl sensorsReader;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        sensorsReader.readSensorConfigurations();

    }
}
