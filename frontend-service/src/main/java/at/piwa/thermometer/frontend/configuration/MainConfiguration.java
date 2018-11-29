package at.piwa.thermometer.frontend.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@EnableAutoConfiguration
@PropertySources({
        @PropertySource("classpath:application.yml")
})
public class MainConfiguration {


    @Bean
    public Module jodaModule() {
        return new JodaModule();
    }

}
