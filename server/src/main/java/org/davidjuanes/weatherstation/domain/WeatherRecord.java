package org.davidjuanes.weatherstation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.util.Date;


@Data
@Builder
@EqualsAndHashCode(callSuper=true)
public class WeatherRecord extends BaseEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;
    private String sensorName;
    private Double temperature;
    private Double humidity;
}
