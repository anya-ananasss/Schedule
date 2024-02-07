package anya.ooptasks.scheduleapp.controller;

import anya.ooptasks.scheduleapp.model.Days;
import anya.ooptasks.scheduleapp.model.Schedule;

import anya.ooptasks.scheduleapp.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ScheduleController {

    ScheduleService service;

    @GetMapping
    public List<Schedule> findAllSchedules (){
        return service.findAllSchedules();
    }

//    @GetMapping
//    public String findAllSchedules(Model model) {
//        List<Schedule> mySchedule = service.findAllSchedules();
//        if (!mySchedule.isEmpty()) {
//            Schedule scheduleForWork = mySchedule.get(0);
//            List <Days> days = getDays(scheduleForWork).entrySet().stream().map(e -> new DayDto (e.getKey(), e.getValue())).collect(Collectors.toList());
//            List<String> times = getTimes(scheduleForWork);
//            SortedMap <Integer, List<String>> scheduleCellsItems = getScheduleItems(scheduleForWork);
//
//
//            model.addAttribute("defaultDays", days);
//            model.addAttribute("defaultTimes", times);
//            model.addAttribute("defaultScheduleItems", scheduleCellsItems.values());
//
//        }
//        return "index";
//    }
//
//    private static SortedMap <Integer, List<String>> getScheduleItems(Schedule scheduleForWork) {
//        SortedMap <Integer, List <String>> scheduleCellsItems = new TreeMap<>();
//        List <Schedule.ScheduleDay> auxDaysList = scheduleForWork.getScheduleDays();
//        for (int i = 0; i < scheduleForWork.getScheduleDays().size(); i++) {
//            Schedule.ScheduleDay auxCurrDay = auxDaysList.get(i);
//            List <Schedule.ScheduleDay.Subject> auxCurrDaySubjects
//                    = auxCurrDay.getSubjects();
//            List <String> innerList = new ArrayList<>();
//            scheduleCellsItems.put(i, innerList);
//            for (int j = 0; j < auxCurrDaySubjects.size(); j++) {
//              String toAdd = auxCurrDaySubjects.get(j).getContent();
//              innerList.add(toAdd);
//              scheduleCellsItems.put(i, innerList);
//            }
//        }
//        return scheduleCellsItems;
//    }
//
//    private static Map<Integer, String> getDays(Schedule scheduleForWork) {
//        int daysAmount = scheduleForWork.getScheduleDays().size();
//       Map<Integer, String> days = new HashMap<>();
//        for (int i = 0; i < daysAmount; i++) {
//            switch (i) {
//                case 0:
//                    days.put(i, "Понедельник");
//                    break;
//                case 1:
//                    days.put(i, "Вторник");
//                    break;
//                case 2:
//                    days.put(i, "Среда");
//                    break;
//                case 3:
//                    days.put(i, "Четверг");
//                    break;
//                case 4:
//                    days.put(i, "Пятница");
//                    break;
//                case 5:
//                    days.put(i, "Суббота");
//                    break;
//                case 6:
//                    days.put(i, "Воскресенье");
//                    break;
//            }
//        }
//        return days;
//    }
//
//    private static List<String> getTimes(Schedule scheduleForWork) {
//        List<String> times = new ArrayList<>();
//        List<Schedule.ScheduleDay> auxDaysList = scheduleForWork.getScheduleDays();
//        int timeRowsAmount = auxDaysList.get(0).getSubjects().size();
//        for (int i = 0; i < timeRowsAmount; i++) {
//            String toAdd =
//                    auxDaysList.get(0).getSubjects().get(i).getSubjectId().getTime();
//            times.add(i, toAdd);
//        }
//        return times;
//    }

//    @PostMapping
//    public void saveSchedule(@RequestBody Schedule schedule) {
//        service.saveSchedule(schedule);
//    }

//    @PutMapping
//    public Schedule updateSchedule(@RequestBody Schedule schedule) {
//        return service.updateSchedule(schedule);
//    }
}
