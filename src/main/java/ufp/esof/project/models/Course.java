package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Explainer> explainers = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.PERSIST)
    private Set<Student> students = new HashSet<>();

    @ManyToOne
    private Degree degree;

    public Course(String name) {
        this.setName(name);
    }
}
