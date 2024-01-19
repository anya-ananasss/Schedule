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
    private List <ScheduleDay> scheduleDays;


    @Getter
    @Setter
    @NoArgsConstructor
    @Table(name = "schedule_day")
    @Entity
    public static class ScheduleDay {
        @Id
        private Integer dayId;


        @OneToMany(mappedBy = "subjectId.scheduleDay", cascade = CascadeType.ALL)
        private List <Subject> subjects;

        @JsonBackReference
        @ManyToOne
        @JoinColumn(name = "schedule_id", referencedColumnName = "scheduleId")
        private Schedule generalSchedule;


        @Getter
        @Setter
        @NoArgsConstructor
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
                private String time;

                @JsonBackReference
                @ManyToOne
                @JoinColumn(name = "schedule_day_id", referencedColumnName = "dayId")
                private ScheduleDay scheduleDay;
            }
        }
    }
}