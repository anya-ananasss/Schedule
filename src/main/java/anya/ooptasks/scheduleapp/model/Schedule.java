package anya.ooptasks.scheduleapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table (name = "schedule")
@Entity
public class Schedule {
    @Id
    private Integer scheduleId;

    @JsonManagedReference
    @OneToMany(mappedBy = "generalSchedule", cascade = CascadeType.ALL)
    private List <Subject> subjects;
}