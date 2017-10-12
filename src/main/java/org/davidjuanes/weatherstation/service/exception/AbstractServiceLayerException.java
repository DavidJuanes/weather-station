package org.davidjuanes.weatherstation.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public abstract class AbstractServiceLayerException extends RuntimeException {

    public AbstractServiceLayerException(String message) {
        super(message);
    }
}
