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

    public void sendTemperature(Temperature temperature) {
        AWSIotMqttClient mqttClient = null;
        try {

            mqttClient = new AWSIotMqttClient(awsIotClientEndpoint, awsIotClientId, credProvider.getCredentials().getAWSAccessKeyId(), credProvider.getCredentials().getAWSSecretKey());
            mqttClient.connect();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JodaModule());
            String payload = objectMapper.writeValueAsString(temperature);

            mqttClient.publish(awsIotBusTopicName, AWSIotQos.QOS0, payload);
            log.info("Send temperature reading: " + temperature);

        } catch (JsonProcessingException | AWSIotException e) {
            log.error("Exception", e);
        }
        finally {
            if(mqttClient != null) {
                try {
                    mqttClient.disconnect();
                } catch (AWSIotException e) {
                    log.error("Exception", e);
                }
            }
        }
    }

}