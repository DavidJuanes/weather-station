package org.davidjuanes.weatherstation.api.exceptions;

import org.davidjuanes.weatherstation.service.exception.SensorNotRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(SensorNotRegisteredException.class)
    public ResponseEntity<ExceptionDto> handleForbidden(Exception e) {
        ExceptionDto exceptionDto = ExceptionDto.builder().date(new Date()).code(HttpStatus.FORBIDDEN.value())
                .message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(exceptionDto);
    }

}
