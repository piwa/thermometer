package at.piwa.thermometer.sensor;

import at.piwa.thermometer.sensor.domain.Temperature;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.iot.client.AWSIotException;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import com.amazonaws.services.iot.client.AWSIotQos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MqttClient {

    @Autowired
    private AWSCredentialsProvider credProvider;

    @Value("${aws.iot.client.endpoint}")
    private String awsIotClientEndpoint;
    @Value("${aws.iot.client.id}")
    private String awsIotClientId;

    @Value("${aws.iot.bus.topic.name}")
    private String awsIotBusTopicName;

    private AWSIotMqttClient mqttClient;

    public void sendTemperature(List<Temperature> temperatures) {

        try {
            log.debug("Send all temperatures start");
            openMqttConnection();

            for (Temperature temperature : temperatures) {
                try {
                    log.debug("Send temperature: " + temperature);
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JodaModule());
                    String payload = objectMapper.writeValueAsString(temperature);

                    mqttClient.publish(awsIotBusTopicName, AWSIotQos.QOS0, payload);
                    log.debug("Send temperature done");
                } catch (JsonProcessingException e) {
                    log.error("Exception", e);
                }
            }

            log.debug("Send all temperatures done");

        } catch (AWSIotException e) {
            log.error("Exception", e);
        }
        finally {
            closeMqttConnection();
        }
    }

    private void closeMqttConnection() {
        if(mqttClient != null) {
            try {
                mqttClient.disconnect();
            } catch (AWSIotException e) {
                log.error("Exception", e);
            }
        }
    }

    private void openMqttConnection() throws AWSIotException {
        mqttClient = new AWSIotMqttClient(awsIotClientEndpoint, awsIotClientId, credProvider.getCredentials().getAWSAccessKeyId(), credProvider.getCredentials().getAWSSecretKey());
        mqttClient.connect();
    }


}