package anya.ooptasks.scheduleapp.repository;

import anya.ooptasks.scheduleapp.model.SingleDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface SingleDayRepository extends JpaRepository<SingleDay, SingleDay.JointId> {

    @Query("SELECT DISTINCT day.id.day FROM SingleDay day ORDER BY day.id.day")
    List<DayOfWeek> findAllDistinctSingleDays();

    @Query("SELECT DISTINCT day.id.startTime FROM SingleDay day ORDER BY day.id.startTime")
    List<LocalTime> findAllDistinctStartTimes();

    @Query("SELECT DISTINCT day.id.endTime FROM SingleDay day ORDER BY day.id.endTime")
    List<LocalTime> findAllDistinctEndTimes();

    @Query("SELECT day FROM SingleDay day ORDER BY day.id.day, day.id.startTime")
    List<SingleDay> findAllOrdered();

    @Modifying
    @Query ("DELETE FROM SingleDay day WHERE day.id = :id")
    void deleteAllById(SingleDay.JointId id);

    @Query ("SELECT day.id FROM SingleDay day")
    List<SingleDay.JointId> findAllIds ();

}