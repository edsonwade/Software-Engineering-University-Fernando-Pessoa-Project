package ufp.esof.project.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tb_appointments")
public class Appointment implements Serializable {
    private static final long serialVersionUID = 7346185811908698013L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    @JsonProperty("id")
    private Long appointmentId;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Student student;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    private Explainer explainer;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "end_time")
    private LocalDateTime expectedEndTime;

    public Appointment() {
        // default constructor
    }

    public Appointment(LocalDateTime startTime, LocalDateTime end) {
        this.startTime = startTime;
        this.expectedEndTime = end;
    }

    public Appointment(Long id, Student student, Explainer explainer, LocalDateTime startTime, LocalDateTime expectedEndTime) {
        this.appointmentId = id;
        this.student = student;
        this.explainer = explainer;
        this.startTime = startTime;
        this.expectedEndTime = expectedEndTime;
    }

    public Appointment(Long id) {
        this.setAppointmentId(id);
    }

    public Appointment(Explainer explainer) {
        this.setExplainer(explainer);
    }


    private boolean isBetween(Appointment other) {
        LocalDateTime appointmentStartTime = other.getStartTime();
        LocalDateTime appointmentEndTime = other.getExpectedEndTime();
        return this.isBetween(appointmentStartTime) || this.isBetween(appointmentEndTime);
    }

    private boolean isBetween(LocalDateTime timeToCheck) {
        return this.startTime.isBefore(timeToCheck) && this.expectedEndTime.isAfter(timeToCheck);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", student=" + student +
                ", explainer=" + explainer +
                ", startTime=" + startTime +
                ", expectedEndTime=" + expectedEndTime +
                '}';
    }
}
