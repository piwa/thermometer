package at.piwa.thermometer.webui.database;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface TemperatureRepository extends CrudRepository<TemperatureDto, String> {

}