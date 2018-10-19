package at.piwa.thermometer.sensorservice.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ContainerCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class CredProviderProduction {

    @Bean
    public AWSCredentialsProvider credProvider() {
        return new ContainerCredentialsProvider();
    }
}