package at.piwa.thermometer.frontend.controller;

import at.piwa.thermometer.frontend.connectors.SensorDto;
import at.piwa.thermometer.frontend.connectors.SensorServiceConnector;
import at.piwa.thermometer.frontend.connectors.TemperatureServiceConnector;
import at.piwa.thermometer.frontend.connectors.TimeSeriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by philippwaibel on 18/01/16.
 */
@Controller
public class TemperatureController {

    @Autowired
    private TemperatureServiceConnector temperatureServiceConnector;
    @Autowired
    private SensorServiceConnector sensorServiceConnector;

    @RequestMapping(value = "/temperatures", method = RequestMethod.GET)
    public String list(Model model){

        List<TimeSeriesDto> timeSeriesDtoList = temperatureServiceConnector.getTemperatureTimeSeries();

        List<SensorDto> allSensors = sensorServiceConnector.getTemperatureTimeSeries();

        List<SensorDto> sensorList = new ArrayList<>();

        for (TimeSeriesDto.TemperatureDto temperatureDto : timeSeriesDtoList.get(0).getTemperatureList()) {
            allSensors.stream().filter(sensor -> temperatureDto.getSensorId().equals(sensor.getId())).findFirst().ifPresent(sensorList::add);
        }

        model.addAttribute("sensors", sensorList);
        model.addAttribute("timeSeries", timeSeriesDtoList);

        return "temperatures";
    }

}
