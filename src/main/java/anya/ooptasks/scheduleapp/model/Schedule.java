package anya.ooptasks.scheduleapp.model;

import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class Schedule {
    @NonNull
    private SortedMap<Integer, ScheduleDay> scheduleMap;

    public List<List<Object>> getScheduleDaysValuesList (){
        List <List<Object>> result =  new ArrayList<>(); //TODO: пока пойдет так, но потом надо бахнуть метод для поиска самого длинного дня в расписании и строить цикл уже по его длине, ставя "" на "выпадающее время" в более коротких днях
        for (ScheduleDay scheduleDay : scheduleMap.values()) { //берем день из расписания....//для конкретного дня создаем list из предметов, ему присвоенных
                result.add(new ArrayList<>(scheduleDay.getDay().values()));
        }
        return result;
    }



    @Getter
    @Setter
    @RequiredArgsConstructor
    @Component
    public static class ScheduleDay {
        @NonNull
        private SortedMap<Integer, Object> day;

    }
}