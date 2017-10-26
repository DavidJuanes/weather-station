package org.davidjuanes.weatherstation.service;

import lombok.extern.slf4j.Slf4j;
import org.davidjuanes.weatherstation.domain.Sensor;
import org.davidjuanes.weatherstation.domain.SensorState;
import org.davidjuanes.weatherstation.domain.WeatherRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SensorStatusChecker {

    @Value("${weather.station.sensor.normalrecordrate}")
    private Long normalRecordRate;
    private Double errorTolerance = 1.2;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private WeatherRecordService weatherRecordService;

    @Scheduled(fixedRate = 30_00)
    public void checkIfSensorsAreIdle() {
        List<Sensor> sensors = sensorService.getAll();
        log.info("Checking the status of {} sensors", sensors.size());
        sensors.stream().filter(this::hasSensorBecomeIdle).forEach(this::setIdleState);
    }

    private Boolean hasSensorBecomeIdle(Sensor sensor) {
        WeatherRecord lastRecord = weatherRecordService.findMostRecentRecordForSensor(sensor.getName());
        //Very weird case, but just in case...
        if (lastRecord == null && sensor.getState() == SensorState.ACTIVE) {
            return true;
        }
        else if (lastRecord == null) {
            return false;
        }
        //TODO FIXME fix NPE in next line.
        log.info("Last record: ", lastRecord);
        log.info("normalRecordRate: {}, errorTolerance: {}", normalRecordRate, errorTolerance);
        if (getDateDiff(lastRecord.getDate(), new Date(), TimeUnit.MILLISECONDS) > normalRecordRate * errorTolerance) {
            return true;
        }
        return false;
    }

    private void setIdleState(Sensor sensor) {
        sensorService.updateSensorState(sensor, SensorState.IDLE);
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
}
