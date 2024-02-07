package anya.ooptasks.scheduleapp.controller;


import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.service.ScheduleService;
import anya.ooptasks.scheduleapp.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SubjectController {
    ScheduleService service;

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

    @PutMapping
    public Schedule updateSubject(@RequestBody Schedule subject) {
        return service.updateSchedule(subject);
    }

}
