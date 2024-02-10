package anya.ooptasks.scheduleapp.repository;

import anya.ooptasks.scheduleapp.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
