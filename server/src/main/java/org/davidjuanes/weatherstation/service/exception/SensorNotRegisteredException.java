package org.davidjuanes.weatherstation.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SensorNotRegisteredException extends AbstractServiceLayerException {

    private static final String ERROR_MESSAGE = "Sensor with name '%s' not registered. Please register it before inputting records.";

    public SensorNotRegisteredException(String sensorName) {
        super(String.format(ERROR_MESSAGE, sensorName));
    }
}
