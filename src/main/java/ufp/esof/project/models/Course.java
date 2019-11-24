package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Explainer> explainers = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.PERSIST)
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    private Degree degree;
}
