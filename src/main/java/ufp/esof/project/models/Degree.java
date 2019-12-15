package ufp.esof.project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
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

    public Degree(String name, Integer totalEcts/*, List<Course> courses, Integer collegeId*/) {
        // TODO: ask professor how to implement logic under.

        this.setName(name);
        this.setTotalEcts(totalEcts);
//        this.setCourses(courses);
//        this.setCollege(college);

//        Optional<College> collegeOptional = CollegeRepo.findById(collegeId);
//        if (collegeOptional.isPresent())
//            this.college = collegeOptional.get();
//        else
//            throw new InvalidCollegeException(collegeId);
    }
}
