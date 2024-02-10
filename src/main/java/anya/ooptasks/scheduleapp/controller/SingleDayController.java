
package anya.ooptasks.scheduleapp.controller;




import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.model.SingleDay;
import anya.ooptasks.scheduleapp.service.SingleDayService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class SingleDayController {
    SingleDayService singleDayService;

    @GetMapping
    public List<SingleDay> findAllSingleDays() {
        return singleDayService.findAllSingleDays();
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
    @DeleteMapping ("remove_last_day")
    public void removeLastDay (){
        singleDayService.deleteAllByDay(singleDayService.findLastDay());
    }

    @Transactional
    @DeleteMapping ("remove_last_time")
    public void removeLastTime (){
        singleDayService.deleteAllByTime(singleDayService.findLastEndTime());
    }



//    @GetMapping ("distinct_ids")
//    public List<SingleDay.JointId> findDistinctIds (){
//       return singleDayService.findDistinctIds();
//    }


    @PostMapping ("add_new_time/{startTime}_{endTime}")
    public String addNewTimeLine (@PathVariable LocalTime startTime, @PathVariable LocalTime endTime){
        singleDayService.addNewTimeline(startTime, endTime);
        return "New timeline added successfully";
    }
//    @PostMapping("add_new_day")
//    public String saveFirstDay (@RequestBody SingleDay singleDay){
//        singleDayService.updateOrSaveSingleDay(singleDay);
//        return "New day added successfully";
//    }
    @PostMapping("add_empty_day")
    public String addEmptyDay (){
        singleDayService.addNewEmptyDay();
        return "Empty day added successfully";
    }

}