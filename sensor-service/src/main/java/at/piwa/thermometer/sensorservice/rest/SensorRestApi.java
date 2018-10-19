package at.piwa.thermometer.sensorservice.rest;

import at.piwa.thermometer.sensorservice.database.Sensor;
import at.piwa.thermometer.sensorservice.database.SensorDBServices;
import at.piwa.thermometer.sensorservice.database.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SensorRestApi {

    @Autowired
    private SensorDBServices sensorDBServices;

    @RequestMapping(value = "/sensor/all", method = RequestMethod.GET)
    public List<Sensor> getAllSensors() {
        return sensorDBServices.findAll();
    }

    @RequestMapping(value = "/sensor", method = RequestMethod.GET)
    public Sensor findOneSensor(@RequestParam("id") String id) {
        return sensorDBServices.findOne(id);
    }

    @RequestMapping(value = "/sensor/id", method = RequestMethod.GET)
    public Sensor getSensorId(@RequestParam("id") String id) {
        return sensorDBServices.findOne(id);
    }

    @PutMapping(value = "/sensor/register")
    public Sensor registerSensor(@RequestBody Sensor sensor) {
        // TODO what happens if the sensor.ID already exists but from another sensor? Is this possible?
        return sensorDBServices.registerSensor(sensor);
    }

}
