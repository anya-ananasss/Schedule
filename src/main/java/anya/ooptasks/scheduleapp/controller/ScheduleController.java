package anya.ooptasks.scheduleapp.controller;


import anya.ooptasks.scheduleapp.model.SingleDay;
import anya.ooptasks.scheduleapp.service.SingleDayService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class ScheduleController {
    private final SingleDayService singleDayService;


    @GetMapping
    public String findAllSingleDays(Model model) {
        List<SingleDay> allScheduleDays = singleDayService.findAllSingleDays();
        List<DayOfWeek> presentWeekDays = singleDayService.findAllDistinctDaysOfWeek();
        List<DayOfWeek> allDays = Arrays.stream(DayOfWeek.values()).toList();
        List<LocalTime> startTimes = singleDayService.findAllDistinctStartTimes();
        List<LocalTime> endTimes = singleDayService.findAllDistinctEndTimes();

        String[][] contents = new String[startTimes.size()][presentWeekDays.size()];

        for (int day = 0; day < presentWeekDays.size(); day++) {
            for (int time = 0; time < startTimes.size(); time++) {
                contents[time][day] = allScheduleDays.get(0).getContent();
                allScheduleDays.remove(0);
            }
        }
        singleDayService.findAllSingleDays();

        model.addAttribute("defaultContent", contents);
        model.addAttribute("defaultDays", presentWeekDays);
        model.addAttribute("allDays", allDays);
        model.addAttribute("defaultSingleDays", allScheduleDays);
        model.addAttribute("defaultStartTimes", startTimes);
        model.addAttribute("defaultEndTimes", endTimes);


        return "index";
    }

    @ResponseBody
    @PostMapping()
    public void examineNewTimeValues(@RequestBody SingleDay singleDay) {
        LocalTime startTime = singleDay.getId().getStartTime();
        LocalTime endTime = singleDay.getId().getEndTime();
        singleDayService.examineNewTimeline(startTime, endTime);
    }

    @ResponseBody
    @Transactional
    @PutMapping()
    public void saveChanges(@RequestBody SingleDay singleDay) {
        singleDayService.saveChanges(singleDay);
    }

    @ResponseBody
    @Transactional
    @DeleteMapping()
    public void deleteLastTime(@RequestBody Integer operationIndex, @RequestBody(required = false) SingleDay singleDayToDelete) {
        if (singleDayToDelete == null) {
            if (operationIndex == 0) {
                singleDayService.deleteAllByTime(singleDayService.findLastEndTime());
            } else if (operationIndex == 1) {
                singleDayService.deleteAllByDay(singleDayService.findLastDay());
            }
        } else {
            if (operationIndex == 0) {
                singleDayService.deleteAllByTime(singleDayToDelete.getId().getEndTime());
            } else if (operationIndex == 1) {
                singleDayService.deleteAllByDay(singleDayToDelete.getId().getDay());
            }

        }
    }

}



//TODO: в индексе забабахать возможность откатиться к прошлой версии - все содержимое таблицы чистится, таблица в базе данных тоже, и через fetch и в цикле все заполняется значениями из origTableArr


