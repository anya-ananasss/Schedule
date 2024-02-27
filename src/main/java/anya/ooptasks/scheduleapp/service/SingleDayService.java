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

    public void examineNewTimeline(LocalTime startTime, LocalTime endTime) {
        List<LocalTime> endTimes = findAllDistinctEndTimes();
        if (startTime == null || endTime == null) {
            throw new RuntimeException("Start or end time is null");
        }
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new RuntimeException("Start time must be less than end time");
        }

        if (endTimes.size() > 1) {
            LocalTime prevEndTime = null;
            for (int i = 1; i < endTimes.size(); i++) {
                if (endTimes.get(i).equals(endTime)) {
                    prevEndTime = endTimes.get(i - 1);
                }
            }
            if (prevEndTime != null) {
                if (prevEndTime.isAfter(startTime)) {
                    throw new RuntimeException("Current start time must be less than previous end time");
                }
            }
        }

        System.out.println("эй гайс у меня все найс");
    }


    public DayOfWeek findLastDay() {
        return repository.findLastDay();
    }


    public LocalTime findLastEndTime() { //TODO: я бы хотела поменять на использование startTime но тут все блять обрушится если я так сделаю
        return repository.findMaxEndTime();
    }

    public void saveChanges(SingleDay updatedSingleDay) {
        repository.save(updatedSingleDay);
    }

    public List<SingleDay> findAllSingleDays() {
        return repository.findAllOrdered();
    }
    public void deleteAllById (SingleDay.JointId id){
        repository.deleteAllById(id);
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

    public List<SingleDay.JointId> findAllIds () {
        return repository.findAllIds();
    }
    public void deleteAllByTime(LocalTime time) {
        repository.deleteAllByTime(time);
    }

    public void deleteAllByDay(DayOfWeek day) {
        repository.deleteAllByDay(day);
    }


}
