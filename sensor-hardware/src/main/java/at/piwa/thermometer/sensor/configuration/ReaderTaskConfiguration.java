package at.piwa.thermometer.sensor.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.iot.client.AWSIotMqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by philippwaibel on 18/01/16.
 */
@Configuration
@EnableScheduling
public class ReaderTaskConfiguration  {


}

