package org.davidjuanes.weatherstation.service;

import lombok.extern.slf4j.Slf4j;
import org.davidjuanes.weatherstation.domain.Sensor;
import org.davidjuanes.weatherstation.domain.SensorState;
import org.davidjuanes.weatherstation.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("singleton")
@Slf4j
public class SensorService {
    @Autowired
    private SensorRepository repository;

    public Sensor register(Sensor sensor) {
        log.info("New sensor register request: " + sensor);
        Sensor existingSensor = repository.findByName(sensor.getName());
        if (existingSensor != null) {
            return existingSensor;
        }
        sensor.setState(SensorState.REGISTERED);
        return repository.insert(sensor);
    }

    public Boolean isRegistered(String sensorName) {
        return repository.findByName(sensorName) != null;
    }

    public List<Sensor> getAll() {
        return repository.findAll();
    }

    public Sensor get(String id) {
        return repository.findOne(id);
    }

    public Sensor findByName(String name) {
        return repository.findByName(name);
    }

    public void delete(String sensorId) {
        repository.delete(sensorId);
    }

    public void update(Sensor sensor) {
        repository.save(sensor);
    }

    public void updateSensorState(Sensor sensor, SensorState newState) {
        sensor.setState(newState);
        update(sensor);
    }
}
