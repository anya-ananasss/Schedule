package anya.ooptasks.scheduleapp.service;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.model.Subject;
import anya.ooptasks.scheduleapp.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository repository;

    public void saveSubject (Subject newSubject){
        repository.save(newSubject);
    }

    public Subject updateSubject(Subject updatedSubject) {
       return repository.save(updatedSubject);
    }
//    //public void deleteSubject (Schedule.ScheduleDay.Subject.SubjectId subjectId){
//        repository.deleteById(subjectId);
//    }

    public List<Subject> findAllSubjects (){
        return  repository.findAll();
    }
}
