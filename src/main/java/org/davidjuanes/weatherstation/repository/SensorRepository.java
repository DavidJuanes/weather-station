package org.davidjuanes.weatherstation.repository;

import org.davidjuanes.weatherstation.domain.Sensor;
import org.davidjuanes.weatherstation.domain.WeatherRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorRepository extends MongoRepository<Sensor, String> {

    public Sensor findByName(String name);
}
