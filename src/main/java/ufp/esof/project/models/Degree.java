package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Integer totalEcts;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.PERSIST)
    private List<Course> courses = new ArrayList<>();

    @ManyToOne
    private College college;
}
