package org.davidjuanes.weatherstation.api;

import org.davidjuanes.weatherstation.api.exceptions.ResourceNotFound;
import org.davidjuanes.weatherstation.domain.WeatherRecord;
import org.davidjuanes.weatherstation.repository.WeatherRecordRepository;
import org.davidjuanes.weatherstation.service.WeatherRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/weatherRecords")
public class WeatherRecordController {

    @Autowired
    private WeatherRecordService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WeatherRecord> addRecord(@RequestBody WeatherRecord record) throws URISyntaxException {
        WeatherRecord weatherRecord = service.addRecord(record);
        return ResponseEntity.created(new URI("/weatherRecords/" + weatherRecord.getId())).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<WeatherRecord> getWeatherRecords(@RequestParam("page") int page, @RequestParam("size") int size) {
        return service.findPaginated(page, size);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WeatherRecord getWeatherRecords(@PathVariable("id") String id) {
        return service.findById(id);
    }
}
