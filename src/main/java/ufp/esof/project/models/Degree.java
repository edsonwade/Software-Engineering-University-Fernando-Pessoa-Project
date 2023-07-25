package ufp.esof.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Degree implements Serializable {
    private static final long serialVersionUID = 1344768769L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private College college;

    public Degree(String name) {
        this.setName(name);
    }

    public Degree(Long id, String name, Set<Course> courses, College college) {
        this.setId(id);
        this.setName(name);
        this.setCourses(courses);
        this.setCollege(college);
    }
}
