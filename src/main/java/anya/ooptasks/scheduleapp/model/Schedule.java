package anya.ooptasks.scheduleapp.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer schedId;


    @OneToMany(mappedBy = "general_schedule", cascade = CascadeType.ALL)
    private List <ScheduleDay> schedule_days;


    @Getter
    @Setter
    @NoArgsConstructor
    @Table(name = "schedule_day")
    @Entity
    public static class ScheduleDay {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer dayId;

        @OneToMany(mappedBy = "subjectId.scheduleDay", cascade = CascadeType.ALL)
        private List <Subject> subjects;


        @ManyToOne
        @JoinColumn(name = "schedule_id")
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

    //ДЖИСОН ЗАПРОС КОТОРЫЙ РАБОТАЕТ НА DAYS:
  //  {
//        "dayId": 1,
//            "subjects": [
//        {
//            "subjectId": {
//            "time": "08:00:00",
//                    "scheduleDay": {
//                "dayId": 1
//            }
//        },
//            "content": "Computer Science"
//        }
    // господи помоги :")
}