package org.davidjuanes.weatherstation;

import org.davidjuanes.weatherstation.domain.WeatherRecord;
import org.davidjuanes.weatherstation.repository.WeatherRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private WeatherRecordRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
