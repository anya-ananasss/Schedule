package anya.ooptasks.scheduleapp.config;

import anya.ooptasks.scheduleapp.model.Schedule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.SortedMap;
import java.util.TreeMap;

@Configuration
public class ScheduleConfig {

    @Bean
    public SortedMap<Integer, Schedule.ScheduleDay> daysMap() {
        return new TreeMap<>();
    }

    @Bean
    public SortedMap<Integer, Object> subjsMap() {
        return new TreeMap<>();
    }
    @Bean
    public Schedule schedule (){
        return new Schedule();
    };
}