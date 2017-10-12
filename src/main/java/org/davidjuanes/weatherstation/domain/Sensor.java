package org.davidjuanes.weatherstation.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode(callSuper=true)
public class Sensor extends BaseEntity {
    private String name;
    private SensorState state;
}
