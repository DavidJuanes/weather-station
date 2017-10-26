package org.davidjuanes.weatherstation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class DailyReportController {

    @RequestMapping(path = "/report/daily", method = GET)
    public ResponseEntity<?> getReport() {
        return ResponseEntity.ok("Hello World");
    }


}
