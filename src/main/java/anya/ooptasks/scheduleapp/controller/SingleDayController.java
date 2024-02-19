
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class SingleDayController {
    SingleDayService singleDayService;

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

//    @PostMapping
//    public String saveSingleDay(@RequestBody SingleDay SingleDay) {
//        singleDayService.saveNewSingleDay(SingleDay);
//        return "SingleDay successfully saved"; //сейвДэй - это добавление нового дня к имеющимся в шкедьюл
//    }

//    @PutMapping
//    public SingleDay updateSingleDay(@RequestBody SingleDay SingleDay) {
//        return singleDayService.updateSingleDay(SingleDay);
//    }

//    @GetMapping("last_time")
//    public LocalTime getLastTime() {
//        return singleDayService.findLastEndTime(); //робит :,)
//    }
//    @GetMapping("last_day")
//    public DayOfWeek getLastDay() {
//        return singleDayService.findLastDay(); //разъееб
//    }
//    @GetMapping("last_time/{currDay}")
//    public LocalTime getLastTimeInDay(@PathVariable DayOfWeek currDay) {
//        return singleDayService.findLastEndTimeInDay(currDay);
//    }

//    @Transactional
//    @DeleteMapping("delete_by_time/{time}")
//    public void deleteAllByTime (@PathVariable LocalTime time){
//        singleDayService.deleteAllByTime(time);
//    }
//    @Transactional
//    @DeleteMapping ("delete_by_day/{day}")
//    public void deleteAllByDay (@PathVariable DayOfWeek day) {
//        singleDayService.deleteAllByDay(day);
//    }

    @Transactional
    @DeleteMapping("remove_last_day")
    public void removeLastDay() {
        singleDayService.deleteAllByDay(singleDayService.findLastDay());
    }

    @Transactional
    @DeleteMapping("remove_last_time")
    public void removeLastTime() {
        singleDayService.deleteAllByTime(singleDayService.findLastEndTime());
    }


//    @GetMapping ("distinct_ids")
//    public List<SingleDay.JointId> findDistinctIds (){
//       return singleDayService.findDistinctIds();
//    }


    @PostMapping("add_new_time/{startTime}_{endTime}")
    public String addNewTimeLine(@PathVariable LocalTime startTime, @PathVariable LocalTime endTime) {
        singleDayService.addNewTimeline(startTime, endTime);
        return "New timeline added successfully";
    }

    //    @PostMapping("add_new_day")
//    public String saveFirstDay (@RequestBody SingleDay singleDay){
//        singleDayService.updateOrSaveSingleDay(singleDay);
//        return "New day added successfully";
//    }
    @PostMapping("add_empty_day")
    public String addEmptyDay() {
        singleDayService.addNewEmptyDay();
        return "Empty day added successfully";
    }

}