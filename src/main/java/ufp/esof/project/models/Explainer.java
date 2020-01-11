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

    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "languages")
    @ToString.Exclude
    @Enumerated
    private Language languages;

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "courses")
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Course> courses = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonBackReference(value = "availabilities")
    @ToString.Exclude
    @OneToMany(mappedBy = "explainer", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Availability> availabilities = new HashSet<>();

    public Explainer(String name) {
        this.setName(name);
    }

    public Explainer(String name, Language language) {
        {
            this.setName(name);
            this.setLanguages(language);
        }
        this.languages = language;
    }

    public Explainer(String name, Set<Course> courses) {
        this.setName(name);
        this.setCourses(courses);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }
}