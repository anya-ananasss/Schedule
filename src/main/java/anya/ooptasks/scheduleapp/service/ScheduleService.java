package anya.ooptasks.scheduleapp.service;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;
    public List<Schedule> findAllSchedules (){
        return  repository.findAll();
    }
    public Optional<Schedule> findById (Integer id){
        return repository.findById(id);
    }

    public void saveSchedule (Schedule newSchedule){
        repository.save(newSchedule);
    }

//    public Schedule updateSchedule(Schedule updatedSchedule) {
//        return repository.save(updatedSchedule);
//    }
    public void deleteAll (){
        repository.deleteAll();
    }
    public void deleteById (Integer id){
        repository.deleteById(id);
    }
}
