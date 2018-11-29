package at.piwa.thermometer.temperatureservice.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class CredProviderDevelopment {

    @Value("${aws.credential.path}")
    private String credentialPath;

    @Bean
    public AWSCredentialsProvider credProvider() {
        AWSCredentialsProvider credentials = new ProfileCredentialsProvider();
        try {
            if (credentials.getCredentials() == null || Strings.isBlank(credentials.getCredentials().getAWSAccessKeyId())) {
                credentials = new ProfileCredentialsProvider(credentialPath + "aws_credentials", "default");
            }
        } catch (Exception ex) {
            credentials = new ProfileCredentialsProvider(credentialPath + "aws_credentials", "default");
        }
        return credentials;
    }
}
