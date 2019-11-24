package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToOne
    private Course course;
}
