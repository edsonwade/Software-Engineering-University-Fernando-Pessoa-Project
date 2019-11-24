package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.PERSIST)
    private List<Availability> availabilities = new ArrayList<>();
}