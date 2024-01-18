package anya.ooptasks.scheduleapp.repository;

import anya.ooptasks.scheduleapp.model.Schedule;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;

public interface ScheduleDayRepository extends JpaRepository <Schedule.ScheduleDay, Integer> {
}