package anya.ooptasks.scheduleapp.service;


import anya.ooptasks.scheduleapp.model.SingleDay;
import anya.ooptasks.scheduleapp.repository.SingleDayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;


@Service
@AllArgsConstructor
public class SingleDayService {
    private final SingleDayRepository repository;

    public void addNewTimeline(LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek) {
        List<LocalTime> endTimes = findAllDistinctEndTimes();
        if (startTime == null || endTime == null) {
            throw new RuntimeException("Start or end time is null");
        }
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new RuntimeException("Start time must be less than end time");
        }

        if (endTimes.size() > 1){
        LocalTime prevEndTime = null;
        for (int i = 0; i < endTimes.size(); i++) {
            if (endTimes.get(i).equals(endTime)){
                prevEndTime=endTimes.get(i-1);
            }
        }
       //TODO - СДЕЛАТЬ ПОЛУЧЕНИЕ ВРЕМЕНИ ПЕРЕД НЫНЕШНИМ
            if (prevEndTime != null) { //TODO: ГЛЯНУТЬ ПОЧЕМУ НУЛЛ МОЖЕТ ПОЛУЧАТЬСЯ
                if (prevEndTime.isAfter(startTime)) {
                    throw new RuntimeException("Current start time must be less than previous end time");
                }
            }
        }

        SingleDay emptyTimeLine = new SingleDay();
        emptyTimeLine.setContent("");
        SingleDay.JointId id = new SingleDay.JointId();
        id.setStartTime(startTime);
        id.setEndTime(endTime);
        id.setDay(dayOfWeek);
        emptyTimeLine.setId(id);
        repository.save(emptyTimeLine);
    }



    public DayOfWeek findLastDay() {
        return repository.findLastDay();
    }



    public LocalTime findLastEndTime() { //TODO: я бы хотела поменять на использование startTime но тут все блять обрушится если я так сделаю
        return repository.findMaxEndTime();
    }


    public void updateSingleDay(SingleDay updatedSingleDay) {
        List<LocalTime> prevStartTimes = repository.findAllDistinctStartTimes();
        List<LocalTime> prevEndTimes = repository.findAllDistinctEndTimes();

        LocalTime startTime = updatedSingleDay.getId().getStartTime();
        LocalTime endTime = updatedSingleDay.getId().getEndTime();

        int occurrencesTime = 0;


        for (int i = 0; i < prevStartTimes.size(); i++) {
            if (startTime.equals(prevStartTimes.get(i)) || endTime.equals(prevEndTimes.get(i))) { //TODO: везде, где нужно, позаменять == на equals
                occurrencesTime++;
            }
        }

        if (occurrencesTime == 0) {
            addNewTimeline(startTime, endTime, updatedSingleDay.getId().getDay());
        } else {
            repository.save(updatedSingleDay);
        }
    }


    public List<SingleDay> findAllSingleDays() {
        return repository.findAllOrdered();
    }

    public List<DayOfWeek> findAllDistinctDaysOfWeek() {
        return repository.findAllDistinctSingleDays();
    }

    public List<LocalTime> findAllDistinctStartTimes() {
        return repository.findAllDistinctStartTimes();
    }

    public List<LocalTime> findAllDistinctEndTimes() {
        return repository.findAllDistinctEndTimes();
    }



    public void deleteAllByTime(LocalTime time) {
        repository.deleteAllByTime(time);
    }

    public void deleteAllByDay(DayOfWeek day) {
        repository.deleteAllByDay(day);
    }



}
