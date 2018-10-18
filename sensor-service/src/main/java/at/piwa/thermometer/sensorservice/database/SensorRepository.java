package at.piwa.thermometer.sensorservice.database;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


@EnableScan
public interface SensorRepository extends CrudRepository<Sensor, String> {

}