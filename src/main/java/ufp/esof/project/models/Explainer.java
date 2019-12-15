package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
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
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.PERSIST)
    private Set<Availability> availabilities = new HashSet<>();
}