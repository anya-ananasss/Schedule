package anya.ooptasks.scheduleapp.repository;

import anya.ooptasks.scheduleapp.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface SubjectRepository extends JpaRepository <Schedule.ScheduleDay.Subject,
        Schedule.ScheduleDay.Subject.SubjectId> {

}
