package anya.ooptasks.scheduleapp.config;

import anya.ooptasks.scheduleapp.model.Schedule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.SortedMap;
import java.util.TreeMap;

@Configuration
public class ScheduleConfig {

    @Bean
    public SortedMap<Integer, Schedule.ScheduleItem> scheduleItemMap() {
        return new TreeMap<>();
    }

    @Bean
    public SortedMap<Integer, Object> objectMap() {
        return new TreeMap<>();
    }
}