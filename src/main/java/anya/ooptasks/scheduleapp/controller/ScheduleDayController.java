//package anya.ooptasks.scheduleapp.controller;
//
//import anya.ooptasks.scheduleapp.model.Schedule;
//import anya.ooptasks.scheduleapp.service.ScheduleDayService;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//public class ScheduleDayController {
//    ScheduleDayService service;
//    @GetMapping
//    public List<Schedule.ScheduleDay> findAllDays() {
//        return service.findAllDays();
//    }
//
//    @PostMapping("save_day")
//    public String saveScheduleDay(@RequestBody Schedule.ScheduleDay day) {
//        service.saveDay(day);
//        return "Day successfully saved";
//    }
//
//    @PutMapping("update_day")
//    public String updateScheduleDay(@RequestBody Schedule.ScheduleDay day) {
//        service.updateDay(day);
//        return "Day updated successfully";
//    }
//}
