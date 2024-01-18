package anya.ooptasks.scheduleapp.service;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository repository;

    //TODO: что мы можем сделать с сабджектами? удалить предмет, добавить предмет, изменить предмет.

    public void saveSubject (Schedule.ScheduleDay.Subject newSubject){
        repository.save(newSubject);
    }

    public Schedule.ScheduleDay.Subject updateSubject(Schedule.ScheduleDay.Subject updatedSubject) {
       return repository.save(updatedSubject);
    }
    public void deleteSubject (Schedule.ScheduleDay.Subject.SubjectId subjectId){
        repository.deleteById(subjectId);
    }

    public List<Schedule.ScheduleDay.Subject> findAllSubjects (){
        return  repository.findAll();
    }
}
