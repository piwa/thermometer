package at.piwa.thermometer.sensor.bootstrap;

import at.piwa.thermometer.sensor.InMemoryCache;
import at.piwa.thermometer.sensor.bootstrap.sensortypes.SensorConfigurations;
import at.piwa.thermometer.sensor.bootstrap.sensortypes.SensorsReaderImpl;
import at.piwa.thermometer.sensor.bootstrap.sensortypes.SensorsWriteImpl;
import at.piwa.thermometer.sensor.connectors.SensorServiceConnector;
import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.SensorConnection;
import at.piwa.thermometer.sensor.reader.I2cReader;
import at.piwa.thermometer.sensor.reader.ReadeTemperatureTask;
import com.pi4j.io.i2c.I2CFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by philippwaibel on 17/01/16.
 */
@Component
@Slf4j
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SensorsReaderImpl sensorsReader;
    @Autowired
    private SensorsWriteImpl sensorsWrite;
    @Autowired
    private InMemoryCache inMemoryCache;
    @Autowired
    private SensorServiceConnector sensorServiceConnector;
    @Autowired
    private ReadeTemperatureTask readeTemperatureTask;
    @Autowired
    private I2cReader i2cReader;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        List<Sensor> sensorConfigurations = sensorsReader.readSensorConfigurations();

        for (Sensor sensorConfiguration : sensorConfigurations) {
            log.debug("Try to register sensor: " + sensorConfiguration.toString());
            Sensor sensor = sensorServiceConnector.registerSensor(sensorConfiguration);
            sensorConfiguration.setId(sensor.getId());
            inMemoryCache.addSensor(sensorConfiguration);
            log.debug("Sensor registered: " + sensorConfiguration.toString());

            if(sensor.getSensorConnection() == SensorConnection.I2C) {
                try {
                    i2cReader.init(sensor);
                } catch (IOException | I2CFactory.UnsupportedBusNumberException | InterruptedException e) {
                    log.error("Exception while initializing the I2C sensor");
                } 
            }
        }

        SensorConfigurations newSensorConfigurations = new SensorConfigurations(inMemoryCache.getSensors());
        sensorsWrite.writeSensorConfigurations(newSensorConfigurations);

        readeTemperatureTask.readTemperatureTask();

    }
}
