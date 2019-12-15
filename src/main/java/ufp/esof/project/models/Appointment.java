package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Language language;

    private LocalTime startTime;

    private DayOfWeek weekday;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Explainer explainer;
}
