package anya.ooptasks.scheduleapp.service;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;

    public void saveSchedule (Schedule newSchedule){
        repository.save(newSchedule);
    }

    public Schedule updateSchedule(Schedule updatedSchedule) {
        return repository.save(updatedSchedule);
    }
    public void deleteSchedule (Integer scheduleId) {
        repository.deleteById(scheduleId);
    }

    public List<Schedule> findAllSchedules (){
        return repository.findAll();
    }
}
