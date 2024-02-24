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
    public void updateSchedule(@RequestBody SingleDay singleDay) {
            singleDayService.updateSingleDay(singleDay);
    }

    @ResponseBody
    @Transactional
    @PutMapping()
    public void saveChanges(@RequestBody SingleDay singleDay){
        singleDayService.updateSingleDay(singleDay);
    }
    @ResponseBody
    @Transactional
    @DeleteMapping()
    public void deleteLastTime (@RequestBody Integer operationIndex){
        if (operationIndex==0) {
            singleDayService.deleteAllByTime(singleDayService.findLastEndTime());
        } else {
            singleDayService.deleteAllByDay(singleDayService.findLastDay());
        }
    }

 }




//TODO: с этими сессиями хибернейт конечно мутная история... надо бы доебать палолегыча попозже




