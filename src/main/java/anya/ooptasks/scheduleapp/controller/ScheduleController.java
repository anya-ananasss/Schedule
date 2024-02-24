package anya.ooptasks.scheduleapp.controller;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.model.SingleDay;
import anya.ooptasks.scheduleapp.service.ScheduleService;
import anya.ooptasks.scheduleapp.service.SingleDayService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {
    private final SingleDayService singleDayService;
    private final ScheduleService scheduleService;





//    //методы для работы с расписаниями (по сути, найти, удалить полностью, удалить одно, добавить новое - обновление происходит через обновление дней)
//    @GetMapping("find_all_schedules")
//    public List<Schedule> findAllSchedules() {
//        return scheduleService.findAllSchedules();
//    }
//
    @PostMapping("save_schedule")
        public String saveSchedule() {
            return "aboba"; //сэйв шкедьюл - самая начальная инициализация, наверное даже и не будет использоваться
        }
//
    @PostMapping()
    public String updateSchedule(@RequestBody SingleDay singleDay) {
            singleDayService.updateSingleDay(singleDay);
        return "Schedule successfully updated";
    }

    @Transactional
    @PutMapping()
    public String saveChanges(@RequestBody SingleDay singleDay){
        singleDayService.updateSingleDay(singleDay);
        return "хуй";
    }
    @Transactional
    @DeleteMapping()
    public String deleteLastTime (){
        singleDayService.deleteAllByTime(singleDayService.findLastEndTime());
        return "success";
    }

//
//    @DeleteMapping ("delete_schedule_by_id/{id}")
//    public void deleteById (@PathVariable Integer id) {
//        scheduleService.deleteById(id);
//    }
//
//     @DeleteMapping ("delete_all_schedules")
//    public void deleteAllSchedules () {
//     scheduleService.deleteAll();
//    }
 }




//TODO: с этими сессиями хибернейт конечно мутная история... надо бы доебать палолегыча попозже




