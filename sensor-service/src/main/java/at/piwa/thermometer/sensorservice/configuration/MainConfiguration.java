package at.piwa.thermometer.sensorservice.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@EnableAutoConfiguration
@PropertySources({
        @PropertySource("classpath:application.yml")
})
public class MainConfiguration {


}
