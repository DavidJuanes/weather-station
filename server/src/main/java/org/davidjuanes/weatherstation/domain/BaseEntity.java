package org.davidjuanes.weatherstation.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public abstract class BaseEntity {
    @Id
    private String id;
}
