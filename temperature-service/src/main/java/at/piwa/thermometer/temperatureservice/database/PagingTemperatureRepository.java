package at.piwa.thermometer.temperatureservice.database;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingTemperatureRepository extends PagingAndSortingRepository<TemperatureDto, String> {


    @EnableScan
    @EnableScanCount
    Page<TemperatureDto> findAll(Pageable pageable);

}