package org.davidjuanes.weatherstation.service;

import org.davidjuanes.weatherstation.api.exceptions.ResourceNotFound;
import org.davidjuanes.weatherstation.domain.SensorState;
import org.davidjuanes.weatherstation.domain.WeatherRecord;
import org.davidjuanes.weatherstation.repository.WeatherRecordRepository;
import org.davidjuanes.weatherstation.service.exception.SensorNotRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope("singleton")
public class WeatherRecordService {

    @Autowired
    private WeatherRecordRepository repository;
    @Autowired
    private SensorService sensorService;

    public WeatherRecord addRecord(WeatherRecord record) {
        //Don't allow records from unregistered sensors
        if (!sensorService.isRegistered(record.getSensorName())) {
            throw new SensorNotRegisteredException(record.getSensorName());
        }
        if (record.getDate() == null) {
            record.setDate(new Date());
        }
        sensorService.updateSensorState(sensorService.findByName(record.getSensorName()), SensorState.ACTIVE);
        return repository.insert(record);
    }

    public Page<WeatherRecord> findPaginated(Integer page, Integer size) {
        Page<WeatherRecord> result = repository.findAll(new PageRequest(page, size));
        if (page > result.getTotalPages()) {
            throw new ResourceNotFound();
        }
        return result;
    }

    public WeatherRecord findById(String id) {
        return repository.findById(id);
    }

    public WeatherRecord findMostRecentRecordForSensor(String sensorName) {
        return repository.findOneBySensorNameOrderByDateAsc(sensorName);
    }
}
