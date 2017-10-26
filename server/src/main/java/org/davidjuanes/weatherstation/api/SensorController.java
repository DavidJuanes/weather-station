package org.davidjuanes.weatherstation.api;

import org.davidjuanes.weatherstation.domain.Sensor;
import org.davidjuanes.weatherstation.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Sensor> register(@RequestBody Sensor sensor) {
        return ResponseEntity.ok(service.register(sensor));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Sensor>> getAllSensors() {
        return ResponseEntity.ok(service.getAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Sensor> getSensor(@PathVariable String id) {
        return ResponseEntity.ok(service.get(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSensor(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
