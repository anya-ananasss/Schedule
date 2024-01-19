package anya.ooptasks.scheduleapp.controller;

import anya.ooptasks.scheduleapp.model.Schedule;

import anya.ooptasks.scheduleapp.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@AllArgsConstructor
public class ScheduleController {

    ScheduleService service;

    @GetMapping
    public String findAllSchedules(Model model) {
        List<Schedule> mySchedule = service.findAllSchedules();
        if (!mySchedule.isEmpty()) {
            Schedule scheduleForWork = mySchedule.get(0);
            List<String> days = getDays(scheduleForWork);
            List<String> times = getTimes(scheduleForWork);
            SortedMap <Integer, List<String>> scheduleCellsItems = getScheduleItems(scheduleForWork);


            model.addAttribute("defaultDays", days);
            model.addAttribute("defaultTimes", times);
            model.addAttribute("defaultScheduleItems", scheduleCellsItems.values());

        }
        return "index";
    }

    private static SortedMap <Integer, List<String>> getScheduleItems(Schedule scheduleForWork) {
        SortedMap <Integer, List <String>> scheduleCellsItems = new TreeMap<>();
        List <Schedule.ScheduleDay> auxDaysList = scheduleForWork.getScheduleDays();
        for (int i = 0; i < scheduleForWork.getScheduleDays().size(); i++) {
            Schedule.ScheduleDay auxCurrDay = auxDaysList.get(i);
            List <Schedule.ScheduleDay.Subject> auxCurrDaySubjects
                    = auxCurrDay.getSubjects();
            List <String> innerList = new ArrayList<>();
            scheduleCellsItems.put(i, innerList);
            for (int j = 0; j < auxCurrDaySubjects.size(); j++) {
              String toAdd = auxCurrDaySubjects.get(j).getContent();
              innerList.add(toAdd);
              scheduleCellsItems.put(i, innerList);
            }
        }
        return scheduleCellsItems;
    }

    private static List<String> getDays(Schedule scheduleForWork) {
        int daysAmount = scheduleForWork.getScheduleDays().size();
        List<String> days = new ArrayList<>();
        for (int i = 0; i < daysAmount; i++) {
            switch (i) {
                case 0:
                    days.add(i, "Понедельник");
                    break;
                case 1:
                    days.add(i, "Вторник");
                    break;
                case 2:
                    days.add(i, "Среда");
                    break;
                case 3:
                    days.add(i, "Четверг");
                    break;
                case 4:
                    days.add(i, "Пятница");
                    break;
                case 5:
                    days.add(i, "Суббота");
                    break;
                case 6:
                    days.add(i, "Воскресенье");
                    break;
            }
        }
        return days;
    }

    private static List<String> getTimes(Schedule scheduleForWork) {
        List<String> times = new ArrayList<>();
        List<Schedule.ScheduleDay> auxDaysList = scheduleForWork.getScheduleDays();
        int timeRowsAmount = auxDaysList.get(0).getSubjects().size();
        for (int i = 0; i < timeRowsAmount; i++) {
            LocalTime auxCurrTimeRow =
                    auxDaysList.get(0).getSubjects().get(i).getSubjectId().getTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            String toAdd = auxCurrTimeRow.format(formatter);
            times.add(i, toAdd);
        }
        return times;
    }

    @PostMapping("save_schedule")
    public void saveSchedule(@RequestBody Schedule schedule) {
        service.saveSchedule(schedule);
    }

    @PutMapping
    public Schedule updateSchedule(@RequestBody Schedule schedule) {
        return service.updateSchedule(schedule);
    }
}
