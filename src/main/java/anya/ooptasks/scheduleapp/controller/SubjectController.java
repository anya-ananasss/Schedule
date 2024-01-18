//package anya.ooptasks.scheduleapp.controller;
//
//
//import anya.ooptasks.scheduleapp.model.Schedule;
//import anya.ooptasks.scheduleapp.service.SubjectService;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//public class SubjectController {
//    SubjectService service;
//
//    @GetMapping
//    public List<Schedule.ScheduleDay.Subject> findAllSubjects() {
//        return service.findAllSubjects();
//    }
//
//    @PostMapping("save_subject")
//    public String saveSubject(@RequestBody Schedule.ScheduleDay.Subject subject) {
//        service.saveSubject(subject);
//        return "Subject successfully saved";
//    }
//
//    @PutMapping("update_subject")
//    public Schedule.ScheduleDay.Subject updateSubject(@RequestBody Schedule.ScheduleDay.Subject subject) {
//        return service.updateSubject(subject);
//    }
//
//}
