package anya.ooptasks.scheduleapp.controller;

import anya.ooptasks.scheduleapp.model.Schedule;
import org.springframework.stereotype.Controller;
import anya.ooptasks.scheduleapp.service.ScheduleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class ScheduleController {

    private Schedule defaultSchedule;

   ScheduleService service;

    //TODO: П/П ЗАДАЕМ В САМИХ ДНЯХ, МАССИВ НАИМЕНОВАНИЙ ВРЕМЕНИ - ВООБЩЕ ОТЕДЛЬНОЕ
    @GetMapping
    String getDefaultSchedule (Model model){
        int DEFAULT_DAYS_AMOUNT = 5;
        int DEFAULT_SUBJS_AMOUNT = 4;

        SortedMap <Integer, Schedule.ScheduleDay> scheduleMap = new TreeMap<>();
        SortedMap <Integer, Object> scheduleDayMap0 = new TreeMap<>();
        SortedMap <Integer, Object> scheduleDayMap1 = new TreeMap<>();
        List <String> timesList = new ArrayList<>();

        scheduleDayMap0.put(0, "ТФКП");
        scheduleDayMap0.put(1, "Дискретная математика");
        scheduleDayMap0.put(2, "Физкультура");
        scheduleDayMap0.put(3, "ООП");


        scheduleDayMap1.put(0, "Алгем");
        scheduleDayMap1.put(1, "КГиГ");
        scheduleDayMap1.put(2, "Политика");
        scheduleDayMap1.put(3, "Английский язык");



        LocalTime time = LocalTime.of(8, 0);
        for (int i = 0; i < DEFAULT_SUBJS_AMOUNT; i++) {
            timesList.add(time.toString());
            time = time.plusHours(1).plusMinutes(40);
        }

        for (int i = 0; i < DEFAULT_DAYS_AMOUNT; i++) {
            if (i%2==0){
                scheduleMap.put(i, new Schedule.ScheduleDay(scheduleDayMap0));
            }
            else {
                scheduleMap.put(i, new Schedule.ScheduleDay(scheduleDayMap1));
            }
        }

        Schedule defaultSchedule = new Schedule(scheduleMap);


        model.addAttribute("defautScheduleItems", defaultSchedule.getScheduleDaysValuesList());
        model.addAttribute("defaultTimes", timesList );
        model.addAttribute("defaultDays", List.of("Пн", "Вт", "Ср", "Чт", "Пт"));
        return "index";
    }

//    @PostMapping (value="/register", method=RequestMethod.POST)
//    public String saveNewSchedule

//TODO БЛИЖАЙШИЕ: НАСТРОИТЬ ОТПРАВКУ (СОХРАНЕНИЕ) ИЗМЕНЕНИЙ, НАПИСАТЬ (ЖМИТЕ ЭНТР ЧТОБЫ СОХРАНИТЬ ИЗМЕНЕНИЯ), СДЕЛАТЬ ЧТОБЫ ПОСЛЕ СУБМИТ ДАТА МЫ ВОЗВРАЩАЛИСЬ НА ТУ ЖЕ СТРАНИЦУ; ВОЗМОЖНО СДЕЛАТЬ КНОПКУ СОХРАНИТЬ ИЗМЕНЕНИЯ;
    //TODO: СДЕЛАТЬ КНОПКУ ДЛЯ УДАЛЕНИЯ/ДОБАВЛЕНИЯ ДНЕЙ НА ОСНОВЕ МАССИВА ДНЕЙ (ДНЕЙ 7 - "+" УХОДИТ, ДНЕЙ 0 - "-" УХОДИТ)
    //TODO: СДЕЛАТЬ УДАЛЕНИЕ/ДОБАВЛЕНИЕ ВРЕМЕНИ; "-" ПРОПАДАЕТ ЕСЛИ СТРОЧЕК ВРЕМЕНИ 0

}

