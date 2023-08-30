package ufp.esof.project.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "tb_courses")
public class Course implements Serializable {
    private static final long serialVersionUID = -2062070712995046595L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Explainer> explainers = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Degree degree;

    public Course() {
        //default constructor
    }

    public Course(String name) {
        this.setCourseName(name);
    }

    public void addExplainer(Explainer explainer) {
        this.explainers.add(explainer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!Objects.equals(courseId, course.courseId)) return false;
        if (!Objects.equals(courseName, course.courseName)) return false;
        if (!Objects.equals(explainers, course.explainers)) return false;
        return Objects.equals(degree, course.degree);
    }

    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (explainers != null ? explainers.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", explainers=" + explainers +
                ", degree=" + degree +
                '}';
    }
}
