package at.piwa.thermometer.temperatureservice.database;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface TemperatureRepository extends CrudRepository<TemperatureDto, String> {

}