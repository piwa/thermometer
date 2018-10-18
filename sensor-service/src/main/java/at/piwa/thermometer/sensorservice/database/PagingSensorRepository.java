package at.piwa.thermometer.sensorservice.database;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingSensorRepository extends PagingAndSortingRepository<Sensor, String> {


    @EnableScan
    @EnableScanCount
    Page<Sensor> findAll(Pageable pageable);

}