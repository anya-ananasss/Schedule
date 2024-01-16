package anya.ooptasks.scheduleapp.service;

import anya.ooptasks.scheduleapp.model.Schedule;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    @NonNull
    Schedule schedule = new Schedule(new TreeMap<>());
    SortedMap<Integer, Schedule.ScheduleDay> scheduleMap = schedule.getScheduleMap();

    public ScheduleItemService scheduleItemService = new ScheduleItemService();


    private void addNewDay(int item, int defaultRowsAmount) { //update
        SortedMap<Integer, Object> emptyDay = new TreeMap<>();
        for (int i = 0; i < defaultRowsAmount; i++) {
            emptyDay.values().add("");
            emptyDay.keySet().add(i);
        }
        this.scheduleMap.put(item, new Schedule.ScheduleDay(emptyDay));
    }

    private void removeChosenDay(int item) {//пусть рядом с названием каждого дня будет минус, пусть при нажатии на этот минус вываливается окно с требованием подтверждения или делаю анду/реду, как гпт предлагал, тут уж что легче будет и на что времени хватит4
        this.scheduleMap.remove(item);
    }//update

    private void addTimeRows() {//update
        for (Schedule.ScheduleDay currDay : this.scheduleMap.values()) {
            scheduleItemService.addTimeRow(currDay, this.scheduleMap.size() - 1);
        }
    }

    private void removeTimeRows() { //update
        for (Schedule.ScheduleDay currDay : this.scheduleMap.values()) {
            scheduleItemService.removeTimeRow(currDay, this.scheduleMap.size() - 1);
        }
    }


    public Schedule initNewSchedule() { //post
        this.schedule.setScheduleMap(new TreeMap<>());
        return schedule;
    } //create

    public void removeSchedule() { //delete
        this.schedule.setScheduleMap(null);
    }

    @Service
    @RequiredArgsConstructor
    public class ScheduleItemService {
        Schedule.ScheduleDay scheduleDay = new Schedule.ScheduleDay(new TreeMap<>());
        SortedMap<Integer, Object> day = scheduleDay.getDay();

        private void addInfoToTime(int row, String info) {
            this.day.put(row, info);
        }

        public void addTimeRow(Schedule.ScheduleDay scheduleDay, int row) {
            scheduleDay.getDay().put(row, "");

        }

        public void removeTimeRow(Schedule.ScheduleDay scheduleDay, int row) {
            scheduleDay.getDay().remove(row);
        }

        public void addNumerator(int row) {
            String prevValue = (String) this.day.get(row);
            List<String> numList = new ArrayList<>();
            numList.add("");
            numList.add(prevValue);
            this.day.put(row, numList);

        }

        public void removeNumerator(int row) {
            ArrayList numList = (ArrayList) this.day.get(row);
            String valueToSave = (String) numList.get(1); //получаем нижнее значение
            this.day.put(row, valueToSave);

        }

        public void addDenumerator(int row) {
            String prevValue = (String) this.day.get(row);
            List<String> denumList = new ArrayList<>();
            denumList.add(prevValue);
            denumList.add("");
            this.day.put(row, denumList);
        }

        public void removeDenumerator(int row) {
            ArrayList numList = (ArrayList) this.day.get(row);
            String valueToSave = (String) numList.get(0); //получаем верхнее значение
            this.day.put(row, valueToSave);
        }
    }
}

//        //нумератор числитель, денумератор - знаменатель


//    операции создания — создание ресурса через метод POST ;
//    операции чтения — возврат представления ресурса через метод GET ;
//    операции редактирования — перезапись ресурса через метод PUT или редактирование через PATCH ;
//    операции удаления — удаление ресурса через метод DELETE .
