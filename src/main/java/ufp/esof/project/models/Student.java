package ufp.esof.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private Set<Appointment> appointments = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Explainer> explainers = new HashSet<>();

    public Student(String name) {
        this.setName(name);
    }

    public void addExplainer(Explainer explainer) {
        this.explainers.add(explainer);
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setStudent(this);
    }
}
