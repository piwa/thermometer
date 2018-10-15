package at.piwa.thermometer.sensor.reader;

import at.piwa.thermometer.sensor.InMemoryCache;
import at.piwa.thermometer.sensor.MqttClient;
import at.piwa.thermometer.sensor.domain.Sensor;
import at.piwa.thermometer.sensor.domain.SensorConnection;
import at.piwa.thermometer.sensor.domain.Temperature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by philippwaibel on 19/01/16.
 */
@Component
@Slf4j
public class ReadeTemperatureTask {

    @Autowired
    private I2cReader i2cReader;
    @Autowired
    private W1Reader w1Reader;
    @Autowired
    private InMemoryCache inMemoryCache;
    @Autowired
    private MqttClient mqttClient;

    @Value("${thermometer.simulation}")
    private boolean simulation;

    public void init() {
        if (!simulation) {
            i2cReader.init();
            w1Reader.init();
        }
    }

    @Scheduled(initialDelay = 10, fixedRate = 3600000)
    public void readTemperatureTask() {

        for (Sensor sensor : inMemoryCache.getSensors()) {
            Temperature temperature = null;
            if (sensor.getSensorConnection() == SensorConnection.I2C) {
                temperature = i2cReader.readTemperature(sensor);
            } else if (sensor.getSensorConnection() == SensorConnection.WIRE_1) {
                temperature = w1Reader.readTemperature(sensor);
            }

            if(temperature != null) {
                mqttClient.sendTemperature(temperature);
            }
        }

    }



}