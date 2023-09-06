package ufp.esof.project.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "tb_availabilitys")
public class Availability implements Serializable {

    private static final long serialVersionUID = 734645678698013L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Long availabilityId;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @Column(name = "availability_start")
    private LocalTime start;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @Column(name = "availability_end")
    private LocalTime  end;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JsonBackReference
    private Explainer explainer;

    public Availability() {
        //default constructor
    }

    public Availability(Long availabilityId, DayOfWeek dayOfWeek, LocalTime start, LocalTime end, Explainer explainer) {
        this.availabilityId = availabilityId;
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
        this.explainer = explainer;
    }

    public Availability(DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
    }

    public boolean contains(Appointment appointment) {
        DayOfWeek dayOfWeek = appointment.getStartTime().getDayOfWeek();
        if (dayOfWeek.equals(this.dayOfWeek)) {
            LocalTime appointmentStart = appointment.getStartTime().toLocalTime();
            LocalTime appointmentEnd = appointment.getExpectedEndTime().toLocalTime();
            return this.contains(appointmentStart, appointmentEnd);
        }
        return false;
    }

    private boolean contains(LocalTime start, LocalTime end) {
        return (this.start.isBefore(start) || this.start.equals(start))
                &&
                (this.end.isAfter(end) || this.end.equals(end));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Availability that = (Availability) o;

        if (!Objects.equals(availabilityId, that.availabilityId)) return false;
        if (dayOfWeek != that.dayOfWeek) return false;
        if (!Objects.equals(start, that.start)) return false;
        if (!Objects.equals(end, that.end)) return false;
        return Objects.equals(explainer, that.explainer);
    }

    @Override
    public int hashCode() {
        int result = availabilityId != null ? availabilityId.hashCode() : 0;
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (explainer != null ? explainer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "availabilityId=" + availabilityId +
                ", dayOfWeek=" + dayOfWeek +
                ", start=" + start +
                ", end=" + end +
                ", explainer=" + explainer +
                '}';
    }
}
