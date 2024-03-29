package anya.ooptasks.scheduleapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class SingleDay {
    @EmbeddedId
    private JointId id;
    private String content;

    @Embeddable
    @Getter
    @Setter
    public static class JointId implements Serializable {
        @Column(columnDefinition = "time without time zone")
        @DateTimeFormat(pattern = "H:mm")
        private LocalTime startTime;
        @Column(columnDefinition = "time without time zone")
        @DateTimeFormat(pattern = "H:mm")
        private LocalTime endTime;
        @Enumerated(EnumType.ORDINAL)
        private DayOfWeek day;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "scheduleId")
    private Schedule schedule;
}


