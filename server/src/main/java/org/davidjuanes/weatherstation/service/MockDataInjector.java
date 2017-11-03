package org.davidjuanes.weatherstation.service;

import org.davidjuanes.weatherstation.domain.Sensor;
import org.davidjuanes.weatherstation.domain.WeatherRecord;
import org.davidjuanes.weatherstation.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
public class MockDataInjector implements CommandLineRunner {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private WeatherRecordService weatherRecordService;

    public void mockData() throws InterruptedException {
        Sensor sensor = createSensor();
        mockDataForSensor(sensor);
    }

    private Sensor createSensor() {
        Sensor sensor = new Sensor();
        sensor.setName("Sensor muy falso");
        return sensorService.register(sensor);
    }
    @Async
    private void mockDataForSensor(Sensor sensor) throws InterruptedException {
        Random rand = new Random();
        for (;;) {
            Double humidity = (double) rand.nextInt(30) + 50;
            Double temperature = (double) rand.nextInt(7) + 20;
            WeatherRecord weatherRecord = WeatherRecord.builder()
                    .humidity(humidity)
                    .temperature(temperature)
                    .sensorName(sensor.getName()).build();
            weatherRecordService.addRecord(weatherRecord);
            Thread.sleep(2000);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        mockData();
    }
}
