package anya.ooptasks.scheduleapp.model;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class Schedule {
    @NonNull
    private SortedMap<Integer, ScheduleItem> scheduleMap;


    @Getter
    @Setter
    @RequiredArgsConstructor
    @Component
    public static class ScheduleItem {
        @NonNull
        private SortedMap<Integer, Object> day;

    }
}