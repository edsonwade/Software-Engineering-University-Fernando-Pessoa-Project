package ufp.esof.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Explainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @Enumerated
    private Language languages;

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.PERSIST)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.PERSIST)
    private Set<Availability> availabilities = new HashSet<>();

    public Explainer(String name) {
        this.setName(name);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}