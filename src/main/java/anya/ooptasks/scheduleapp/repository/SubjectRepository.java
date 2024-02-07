package anya.ooptasks.scheduleapp.repository;

import anya.ooptasks.scheduleapp.model.Schedule;
import anya.ooptasks.scheduleapp.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

public interface SubjectRepository extends JpaRepository <Subject,
       String> {

}
