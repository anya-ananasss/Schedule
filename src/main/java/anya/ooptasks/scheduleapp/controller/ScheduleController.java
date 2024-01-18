package anya.ooptasks.scheduleapp.controller;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.service.ScheduleDayService;

import anya.ooptasks.scheduleapp.service.ScheduleService;
import lombok.AllArgsConstructor;
//import anya.ooptasks.scheduleapp.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.*;

@RestController
@AllArgsConstructor
public class ScheduleController {

    ScheduleService service;

    @GetMapping
    public List<Schedule> findAllSchedules() {
        return service.findAllSchedules();
    }

    @PostMapping("save_schedule")
    public String saveSchedule(@RequestBody Schedule schedule) {
        service.saveSchedule(schedule);
        return "Schedule successfully saved";
    }

    @PutMapping("update_schedule")
    public Schedule updateSchedule(@RequestBody Schedule schedule) {
        return service.updateSchedule(schedule);
    }
}





//
////    @GetMapping
////    public List<Schedule> findAllSchedules() {
////        return service.findAllSchedules();
////    }
////
////
////    @PostMapping
////    public String addNewSchedule(@RequestBody Schedule schedule) {
////        service.addNewSchedule(schedule);
////        return "Student successfully saved";
////    }
//
//
//
//    @GetMapping
//    public Optional<Schedule> initTestSchedule (){
//        schedule.setSchedId(0);
//        schedule.setSchedule_days(new ArrayList<>());
//
//
//        Schedule.ScheduleDay newDay = new Schedule.ScheduleDay();
//        newDay.setGeneral_schedule(schedule);
//        newDay.setDayId(0);
//
//
//        Schedule.ScheduleDay.Subject subject1 = new Schedule.ScheduleDay.Subject();
//        Schedule.ScheduleDay.Subject subject2 = new Schedule.ScheduleDay.Subject();
//        Schedule.ScheduleDay.Subject subject3 = new Schedule.ScheduleDay.Subject();
//
//        Schedule.ScheduleDay.Subject.SubjectId subjectId1 =
//                new Schedule.ScheduleDay.Subject.SubjectId();
//        Schedule.ScheduleDay.Subject.SubjectId subjectId2 =
//                new Schedule.ScheduleDay.Subject.SubjectId();
//        Schedule.ScheduleDay.Subject.SubjectId subjectId3 =
//                new Schedule.ScheduleDay.Subject.SubjectId();
//
//        subject1.setContent("ТФКП");
//        subject2.setContent("Алгем");
//        subject3.setContent("Дискретная атематика");
//
//
//        subjectId1.setTime(LocalTime.of(8,0));
//        subjectId2.setTime(LocalTime.of(9,45));
//        subjectId3.setTime(LocalTime.of(11,20));
//
//
//
//        subject1.setSubjectId(subjectId1);
//        subject2.setSubjectId(subjectId2);
//        subject3.setSubjectId(subjectId3);
//
//        List <Schedule.ScheduleDay.Subject> subjects = Arrays.asList(subject1, subject2, subject3);
//
//        for (int i = 0; i < subjects.size(); i++) {
//            subjects.get(i).getSubjectId().setScheduleDay(newDay);
//        }
//        newDay.setSubjects(subjects);
//
//
//        schedule.setSchedule_days(Arrays.asList(newDay));
//
//
//
//        service.addNewTimeLine(schedule, LocalTime.of(13, 25));
//        return service.findById(0);
//    }
//}
//
//
//
//

