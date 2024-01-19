package anya.ooptasks.scheduleapp.service;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.repository.ScheduleDayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleDayService {
    private final ScheduleDayRepository repository;
    public void saveDay (Schedule.ScheduleDay newDay){
        repository.save(newDay);
    }

    public Schedule.ScheduleDay updateDay(Schedule.ScheduleDay updatedDay) {
        return repository.save(updatedDay);
    }
    public void deleteDay (Integer dayId) {
        repository.deleteById(dayId);
    }

    public List<Schedule.ScheduleDay> findAllDays (){
        return repository.findAll();
    }
}
