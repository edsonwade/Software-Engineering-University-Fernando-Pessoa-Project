package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
public class Availability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private DayOfWeek weekday;

    private LocalTime startTime,
            endTime;

    @ManyToOne
    private Explainer explainer;
}
