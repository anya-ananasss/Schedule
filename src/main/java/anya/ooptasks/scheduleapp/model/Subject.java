package anya.ooptasks.scheduleapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Table(name = "subject")
@Entity
public class Subject {
    @GeneratedValue
    @Transient
    private int intId;
    @Transient
    private Days currDay;
    @Id
    private String id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "scheduleId")
    private Schedule generalSchedule;

    public String getId() {
        return this.intId+(this.currDay.toString());
    }
}