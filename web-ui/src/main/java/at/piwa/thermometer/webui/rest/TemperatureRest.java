package at.piwa.thermometer.webui.rest;

import at.piwa.thermometer.webui.database.Temperature;
import at.piwa.thermometer.webui.database.TemperatureServices;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class TemperatureRest {


    @Autowired
    private TemperatureServices temperatureService;

    @Setter
    @Getter
    private class Row {
        private List<String> column;
    }

    // TODO improve this function
    @RequestMapping("/rest/temperatures/all")
    public String getAllTemperatures() {

        List<Temperature> temperatureList = temperatureService.findAll();
        temperatureList.sort(Comparator.comparing(Temperature::getTime));

        Set<String> sensorIds = temperatureList.stream().map(Temperature::getSensorId).collect(Collectors.toSet());

        Map<String, List<Temperature>> sensorTemperatureMap = new HashMap<>();
        for (String sensorId : sensorIds) {
            List<Temperature> sensorTemperature = temperatureList.stream().filter(temperature -> temperature.getSensorId().equals(sensorId)).collect(Collectors.toList());
            sensorTemperatureMap.put(sensorId, sensorTemperature);
        }

        String firstSensorId = sensorIds.stream().findFirst().orElse("");   // TODO
        List<Temperature> firstSensorTemperatures = sensorTemperatureMap.get(firstSensorId);

        Map<String, PolynomialSplineFunction> splineFunctionMap = new HashMap<>();
        for (Map.Entry<String, List<Temperature>> stringListEntry : sensorTemperatureMap.entrySet()) {
            double[] sensor2Temp = stringListEntry.getValue().stream().mapToDouble(Temperature::getTemperature).toArray();
            double[] sensor2Time = stringListEntry.getValue().stream().mapToDouble(temperature -> (double) temperature.getTime().getMillis()).toArray();
            SplineInterpolator splineInterpolatorSensor2 = new SplineInterpolator();
            PolynomialSplineFunction splineFunction = splineInterpolatorSensor2.interpolate(sensor2Time, sensor2Temp);
            splineFunctionMap.put(stringListEntry.getKey(), splineFunction);
        }

        splineFunctionMap.remove(firstSensorId);
        List<TimeSeriesDto> timeSeriesDtoList = new ArrayList<>();
        for (Temperature firstSensorTemperature : firstSensorTemperatures) {
            try {
                TimeSeriesDto dtoEntry = new TimeSeriesDto();
                dtoEntry.setTime(firstSensorTemperature.getTime());
                dtoEntry.getTemperatureList().add(firstSensorTemperature.getTemperature());

                for (Map.Entry<String, PolynomialSplineFunction> splineFunctionEntrySet : splineFunctionMap.entrySet()) {
                    double sensorInterpolateTemp = splineFunctionEntrySet.getValue().value(firstSensorTemperature.getTime().getMillis());
                    if (sensorInterpolateTemp < 0) {
                        dtoEntry.getTemperatureList().add(0.0);
                    }
                    dtoEntry.getTemperatureList().add(sensorInterpolateTemp);
                }

                timeSeriesDtoList.add(dtoEntry);
            } catch (Exception e) {
                log.error("Exception", e);      // TODO
            }
        }

        return createCsvDocument(timeSeriesDtoList);


    }

    private String createCsvDocument(List<TimeSeriesDto> timeSeriesDtoList) {
        StringBuilder builder = new StringBuilder();
        builder.append("DateTime").append(",").append("Sensor 1").append(",").append("Sensor 2").append("\n");
        timeSeriesDtoList.forEach(timeSeriesDto -> builder.append(timeSeriesDto.getTime().toString()).append(",").append(timeSeriesDto.getTemperatureList().get(0)).append(",").append(timeSeriesDto.getTemperatureList().get(1)).append("\n"));

        return builder.toString();
    }

}
