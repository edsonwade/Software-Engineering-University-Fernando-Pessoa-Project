package ufp.esof.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Explainer implements Serializable {

    private static final long serialVersionUID = -7007880810353385226L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "courses")
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Course> courses = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "explainer")
    private Set<Language> language = new HashSet<>();


    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "availabilities")
    @ToString.Exclude
    @OneToMany(mappedBy = "explainer", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Availability> availabilities = new HashSet<>();

    public Explainer(String name) {
        this.setName(name);
    }
    @SuppressWarnings("unused")
    public Explainer(String name, String languages  ) {
        this.setName(name);
        this.setLanguage(language);
    }

    public Explainer(String name, Set<Course> courses) {
        this.setName(name);
        this.setCourses(courses);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}