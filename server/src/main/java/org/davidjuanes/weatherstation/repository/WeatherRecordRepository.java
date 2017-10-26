package org.davidjuanes.weatherstation.repository;

import org.davidjuanes.weatherstation.domain.WeatherRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WeatherRecordRepository extends MongoRepository<WeatherRecord, String> {
    public WeatherRecord findById(String id);
    public WeatherRecord findOneBySensorNameOrderByDateAsc(String sensorName);
}
