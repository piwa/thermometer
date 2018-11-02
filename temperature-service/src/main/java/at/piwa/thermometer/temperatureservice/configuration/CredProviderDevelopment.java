package at.piwa.thermometer.temperatureservice.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class CredProviderDevelopment {

    @Bean
    public AWSCredentialsProvider credProvider() {
        return new ProfileCredentialsProvider();
    }
}
