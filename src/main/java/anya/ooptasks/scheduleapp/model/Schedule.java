package anya.ooptasks.scheduleapp.model;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

@Getter
@Setter
@NoArgsConstructor
@Table (name = "schedule")
@Entity
public class Schedule {
    @Id
     private Integer schedId;
    @OneToMany(mappedBy = "general_schedule", cascade = CascadeType.ALL)
    private List <ScheduleDay> schedule;


    @Getter
    @Setter
    @NoArgsConstructor
    @Table(name = "schedule_day")
    @Entity
    public class ScheduleDay {
        @Id
        private Integer dayId;

        @OneToMany(mappedBy = "subjectId.scheduleDay", cascade = CascadeType.ALL)
        private List <Subject> subjects;


        @ManyToOne
        @JoinColumn(name = "schedule")
        private Schedule general_schedule;


        @Getter
        @Setter
        @NoArgsConstructor //todo:зачем?....
        @Table (name = "subject")
        @Entity
        public static class Subject {
            @EmbeddedId
            private SubjectId subjectId;

            private String content;


            @Getter
            @Setter
            @Embeddable
            public static class SubjectId implements Serializable {
                private LocalTime time;
                @ManyToOne
                @JoinColumn(name = "schedule_day_id", referencedColumnName = "dayId")

                private ScheduleDay scheduleDay;
            }
        }
    }
    //предмет - ПК
}