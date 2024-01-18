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

//    SubjectService subjectService;
//    ScheduleDayService scheduleDayService;
//
//    public void addNewSchedule (Schedule newSchedule){
//        repository.save(newSchedule);
//    }
//    public void deleteSchedule(Schedule schedule){
//        repository.delete(schedule);
//    }
//
//    public void addNewTimeLine (Schedule schedule, LocalTime newTime){
//        List<Schedule.ScheduleDay> daysInSchedule = schedule.getSchedule_days();
//        Schedule.ScheduleDay.Subject newEmptySubject =
//                new Schedule.ScheduleDay.Subject();
//        newEmptySubject.setContent("");
//        Schedule.ScheduleDay.Subject.SubjectId newEmptySubjectId =
//                new Schedule.ScheduleDay.Subject.SubjectId();
//        newEmptySubjectId.setTime(newTime);
//            for (int i = 0; i < daysInSchedule.size(); i++) {
//                Schedule.ScheduleDay currDay = daysInSchedule.get(i);
//                newEmptySubjectId.setScheduleDay(currDay);
//                newEmptySubject.setSubjectId(newEmptySubjectId);
//                subjectService.saveSubject(newEmptySubject);
//            }
//    }
//    public void removeLastTimeLine (Schedule schedule){
//        List<Schedule.ScheduleDay> daysInSchedule = schedule.getSchedule_days();
//        Schedule.ScheduleDay.Subject.SubjectId subjectToDeleteId =
//                new Schedule.ScheduleDay.Subject.SubjectId();
//        for (int i = 0; i < daysInSchedule.size(); i++) {
//            Schedule.ScheduleDay currDay = daysInSchedule.get(i);
//            subjectToDeleteId.setTime(scheduleDayService.findLastTimeLineInDay(currDay));
//            subjectToDeleteId.setScheduleDay(currDay);
//            subjectService.deleteSubject(subjectToDeleteId);
//        }
//    }
//    public List <Schedule> findAllSchedules(){
//        return repository.findAll();
//    }
//    public Optional<Schedule> findById (Integer id){
//        return repository.findById(id);
//    }
//    public void addNewScheduleDay (Schedule schedule, Schedule.ScheduleDay newDay){
//        List <Schedule.ScheduleDay> days = schedule.getSchedule_days();
//        days.add(newDay);
//        repository.save(schedule);
//    }

