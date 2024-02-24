package anya.ooptasks.scheduleapp.service;


import anya.ooptasks.scheduleapp.model.SingleDay;
import anya.ooptasks.scheduleapp.repository.SingleDayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SingleDayService {
    private final SingleDayRepository repository;

    public void addNewTimeline(LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek) {
        List<DayOfWeek> presentDays = findAllDistinctDaysOfWeek();
        List<LocalTime> endTimes = findAllDistinctEndTimes();
        if (startTime == null || endTime == null) {
            throw new RuntimeException("Start or end time is null");
        }
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            System.out.println("сиська пиписька");
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
                    System.out.println("зига свастика");
                    throw new RuntimeException("Current start time must be less than previous end time");
                }
            }
        }

        System.out.println("abobus");
        SingleDay emptyTimeLine = new SingleDay();
        emptyTimeLine.setContent("");
        SingleDay.JointId id = new SingleDay.JointId();
        id.setStartTime(startTime);
        id.setEndTime(endTime);
        id.setDay(dayOfWeek);
        emptyTimeLine.setId(id);
        repository.save(emptyTimeLine);
        return;

//        SingleDay emptyTimeLine = new SingleDay();
//        emptyTimeLine.setContent("");
//        //TODO: посмотреть как сюда привязать шкедьюл айди, и по сути все.... удаление/добавление строки времени есть, удаление/добавление дня - тоже, можно и фронт делать
//
//        SingleDay.JointId id = new SingleDay.JointId();
//        id.setStartTime(startTime);
//        id.setEndTime(endTime);
//
//        for (int i = 0; i < presentDays.size(); i++) {
//            id.setDay(presentDays.get(i));
//            emptyTimeLine.setId(id);
//            repository.save(emptyTimeLine);
        //  }


        //проверили, что временные границы адекватные и что они не наслаиваются на предыдущие.
        // после проверок -
        //добавить в каждый имеющийся день циклом по новой строке ньюСинглДэй -
        // (время начала, время конца, нынешний день недели, content "" )
    }

    public void addNewEmptyDay() {
        List<LocalTime> allPrevStartTimes = repository.findAllDistinctStartTimes();
        List<LocalTime> allPrevEndTimes = repository.findAllDistinctEndTimes();
        int timesAmount = allPrevStartTimes.size();

        SingleDay emptyCellTemplate = new SingleDay();
        SingleDay.JointId id = new SingleDay.JointId();

        emptyCellTemplate.setContent("");

        List<DayOfWeek> presentDaysList = repository.findAllDistinctSingleDays();

        if (presentDaysList.isEmpty()) {
            id.setDay(DayOfWeek.MONDAY);
        } else {
            int presentDaysAmount = presentDaysList.size();
            DayOfWeek lastAddedDay = presentDaysList.get(presentDaysAmount - 1);
            id.setDay(DayOfWeek.values()[lastAddedDay.ordinal() + 1]);
        }

        for (int i = 0; i < timesAmount; i++) {
            LocalTime currPrevStartTime = allPrevStartTimes.get(i);
            LocalTime currPrevEndTime = allPrevEndTimes.get(i);
            id.setStartTime(currPrevStartTime);
            id.setEndTime(currPrevEndTime);

            emptyCellTemplate.setId(id);

            repository.save(emptyCellTemplate);
        }
    }

//    public void updateOrSaveSingleDay(SingleDay singleDay) {
//        List<SingleDay.JointId> distinctIds = findDistinctIds();
//        //если совпадет с имеющимся id, то просто апдейтим
//        for (int i = 0; i < distinctIds.size(); i++) {
//            if (singleDay.getId().equals(distinctIds.get(i))) {
//                repository.save(singleDay);
//                break;
//            }
//        }
//        //если нет - то делаем новую запись; если это новый день, то дополняем его пустыми ячейками времени по образцу предыдущего,
//        //если новое время...  а новое время мы по сути и не сможем добавить из фронтэндовой части хэхэхэ)))
//        List<LocalTime> allPrevStartTimes = repository.findAllDistinctStartTimes();
//        List<LocalTime> allPrevEndTimes = repository.findAllDistinctEndTimes();
//        int timesAmount = allPrevStartTimes.size();
//
//        SingleDay emptyCellTemplate = new SingleDay();
//        SingleDay.JointId id = new SingleDay.JointId();
//
//        emptyCellTemplate.setContent("");
//
//        id.setDay(singleDay.getId().getDay());
//        emptyCellTemplate.setSchedule(singleDay.getSchedule());
//
//        for (int i = 0; i < timesAmount; i++) {
//            LocalTime currPrevStartTime = allPrevStartTimes.get(i);
//            LocalTime currPrevEndTime = allPrevEndTimes.get(i);
//
//            if (currPrevStartTime != singleDay.getId().getStartTime() &&
//                    currPrevEndTime != singleDay.getId().getEndTime()) {
//                id.setStartTime(currPrevStartTime);
//                id.setEndTime(currPrevEndTime);
//
//                emptyCellTemplate.setId(id);
//
//                repository.save(emptyCellTemplate);
//            } else {
//                repository.save(singleDay);
//            }
//        }
//
////TODO: в этот метод и в метод для линии времени добавить строки с инициализацией шкедьюлАйди
//
//    }

    //    public void saveNewSingleDay(SingleDay newSingleDay){
//        LocalTime startTime = newSingleDay.getId().getStartTime();
//        LocalTime endTime = newSingleDay.getId().getEndTime();
//        if (startTime==null || endTime ==null){
//            throw new RuntimeException("Start or end time is null");
//        }
//        if (startTime.isAfter(endTime) || startTime.equals(endTime)){
//            throw new RuntimeException("Start time must be less than end time");
//        }
//
//
//
//        LocalTime prevEndTimeThisDay = findLastEndTimeInDay(newSingleDay.getId().getDay());
//        if (prevEndTimeThisDay!=null){
//            if(prevEndTimeThisDay.isAfter(startTime)){
//                throw new RuntimeException("Current start time must be less than previous end time");
//            }
//        }
//
//        repository.save(newSingleDay);
//    }
    public DayOfWeek findLastDay() {
        return repository.findLastDay();
    }

//    public LocalTime findLastStartTime() {
//        return repository.findMaxStartTime();
//    }

    public LocalTime findLastEndTime() {
        return repository.findMaxEndTime();
    }

    public LocalTime findLastEndTimeInDay(DayOfWeek currDay) {
        return repository.findMaxEndTimeInDay(currDay);
    }

    public void updateSingleDay(SingleDay updatedSingleDay) {
        List<LocalTime> prevStartTimes = repository.findAllDistinctStartTimes();
        List<LocalTime> prevEndTimes = repository.findAllDistinctEndTimes();
        List<DayOfWeek> presentDays = repository.findAllDistinctSingleDays();

        LocalTime startTime = updatedSingleDay.getId().getStartTime();
        LocalTime endTime = updatedSingleDay.getId().getEndTime();
        DayOfWeek day = updatedSingleDay.getId().getDay();

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

//
//    public List<SingleDay.JointId> findDistinctIds() {
//        return repository.findDistinctIds();
//    }

    public void deleteAllByTime(LocalTime time) {
        repository.deleteAllByTime(time);
    }

    public void deleteAllByDay(DayOfWeek day) {
        repository.deleteAllByDay(day);
    }

    public void deleteLastElement(Object object) {
        if (object instanceof DayOfWeek) {
            repository.deleteAllByDay((DayOfWeek) object);
        } else if (object instanceof LocalTime) {
            repository.deleteAllByTime((LocalTime) object);
        }
    }


}
