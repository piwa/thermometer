package at.piwa.thermometer.sensor.connectors;

import at.piwa.thermometer.sensor.domain.Sensor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
@Slf4j
public class SensorServiceConnector {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${connector.sensor.service.url}")
    private String url;

    public Sensor registerSensor(Sensor sensor) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Sensor> entity = new HttpEntity<>(sensor,headers);
        ResponseEntity<Sensor> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, Sensor.class);
            return responseEntity.getBody();
        } catch (RestClientException e) {
            log.error("Exception", e);
        }

        return null;

    }



}
