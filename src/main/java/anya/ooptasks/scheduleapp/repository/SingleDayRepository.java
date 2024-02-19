package anya.ooptasks.scheduleapp.repository;

import anya.ooptasks.scheduleapp.model.SingleDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface SingleDayRepository extends JpaRepository<SingleDay, SingleDay.JointId> {
    @Modifying
    @Query("DELETE FROM SingleDay day WHERE day.id.endTime = :time")
    void deleteAllByTime(LocalTime time);

    @Modifying
    @Query("DELETE FROM SingleDay day WHERE day.id.day = :day")
    void deleteAllByDay(DayOfWeek day);

    @Query("SELECT DISTINCT day.id.startTime FROM SingleDay day WHERE day.id.startTime = (SELECT MAX(day2.id.startTime) FROM SingleDay day2)")
    LocalTime findMaxStartTime();

    @Query("SELECT DISTINCT day.id.endTime FROM SingleDay day WHERE day.id.endTime = (SELECT MAX(day2.id.endTime) FROM SingleDay day2)")
    LocalTime findMaxEndTime();

    @Query("SELECT day.id.startTime FROM SingleDay day WHERE day.id.day = :currDay AND day.id.startTime = (SELECT MAX(day2.id.startTime) FROM SingleDay day2)")
    LocalTime findMaxStartTimeInDay(DayOfWeek currDay);

    @Query("SELECT day.id.endTime FROM SingleDay day WHERE day.id.day = :currDay AND day.id.endTime = (SELECT MAX(day2.id.endTime) FROM SingleDay day2)")
    LocalTime findMaxEndTimeInDay(DayOfWeek currDay);

    @Query("SELECT DISTINCT day.id.day FROM SingleDay day WHERE day.id.day = (SELECT MAX(day2.id.day) FROM SingleDay day2)")
    DayOfWeek findLastDay();

    @Query ("SELECT DISTINCT day.id.day FROM SingleDay day ORDER BY day.id.day")
    List<DayOfWeek> findAllDistinctSingleDays();

    @Query ("SELECT DISTINCT day.id.startTime FROM SingleDay day ORDER BY day.id.startTime")
    List<LocalTime> findAllDistinctStartTimes();

    @Query ("SELECT DISTINCT day.id.endTime FROM SingleDay day ORDER BY day.id.endTime")
    List<LocalTime> findAllDistinctEndTimes();


    @Query("SELECT DISTINCT day.id FROM SingleDay day")
    List<SingleDay.JointId> findDistinctIds();

    @Query ("SELECT day FROM SingleDay day ORDER BY day.id.day, day.id.startTime")
    List <SingleDay> findAllOrdered();

    //TODO: возможно потом можно будет переработать все или некоторое вышеимеющееся в просто строчкой задание, без куери
}