package at.piwa.thermometer.frontend.connectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
@Slf4j
public class SensorServiceConnector {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${connector.sensor.service.url}")
    private String url;

    public List<SensorDto> getTemperatureTimeSeries() throws RestClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<List<SensorDto>> responseEntity = null;
        responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<SensorDto>>() {});
        return responseEntity.getBody();
    }


}
