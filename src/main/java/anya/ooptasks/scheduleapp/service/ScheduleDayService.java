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
//  //  private final SubjectService subjServ;
//
//    //todo: что мы можем сделать с днем?
//    // +добавить в один день новое время (добавить новый сабжект - айди дня/новое время, пустой контент) (в htmle при добавлении нового дня сразу заполнять строку формами)
//    // -удалить последнее время (обратиться к сабджекту, удалить у нее последнюю по время/айди дня строку)
//    // удалить последний день (вырезать из сабджект все, что связано с последним айди дня)
//    // добавить новый п/п день (добавить в садбжект столько пустых контент с новым айди дня, сколько их в предыдущем дне)
//    // +обновить день = обновить имеющиеся в нем предметы
//
////    public void addNewTime (LocalTime newTime){
////        List<Schedule.ScheduleDay> days =
////              scheduleDayRepository.findAll();
////        for (Schedule.ScheduleDay currDay : days) {
////            Schedule.ScheduleDay.Subject newSubject =
////                    new Schedule.ScheduleDay.Subject();
////            Schedule.ScheduleDay.Subject.SubjectId newSubjectId =
////                    new Schedule.ScheduleDay.Subject.SubjectId();
////            newSubjectId.setTime(newTime);
////            newSubjectId.setScheduleDay(currDay);
////            newSubject.setSubjectId(newSubjectId);
////            newSubject.setContent("");
////
////           subjServ.addSubject(newSubject);
////        }
////    }
//    //todo: конечно нужно сделать ограничение чтобы нельзя было добавить время раньше последнего
//
//
//     public void updateContentForSubjects(Schedule.ScheduleDay scheduleDay, String newContent) {
//        List<Schedule.ScheduleDay.Subject> subjects =
//                scheduleDay.getSubjects();
//        for (Schedule.ScheduleDay.Subject subject : subjects) {
//            subject.setContent(newContent);
//            subjServ.updateSubject(subject);
//        }
//    }
//
//    //todo: работа со временем и апдейтом 99% срань
//    public void addNewDay (Schedule.ScheduleDay newEmptyDay){
//        repository.save(newEmptyDay);
//    }
//    //TODO: тож ебанина
//    public void removeLastDay(){
//        repository.deleteById(repository.findAll().size()-1);
//    }
//    public List<Schedule.ScheduleDay> findAllDays (){
//        return repository.findAll();
//    }
//    public LocalTime findLastTimeLineInDay (Schedule.ScheduleDay scheduleDay){
//       return repository.findMaxTimeByDayId(scheduleDay.getDayId());
//    }


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
