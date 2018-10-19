package at.piwa.thermometer.sensor.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CredProviderDevelopment {

    @Bean
    AWSCredentialsProvider credProvider() {
        return new ProfileCredentialsProvider();
    }
}
