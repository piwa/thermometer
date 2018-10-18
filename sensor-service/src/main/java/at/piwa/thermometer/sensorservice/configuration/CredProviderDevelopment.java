package at.piwa.thermometer.sensorservice.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class CredProviderDevelopment {

    @Value("${aws.username}")
    protected String awsUsername;

    @Bean
    public AWSCredentialsProvider credProvider() {
        return new ProfileCredentialsProvider(awsUsername);
    }
}
