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
@Table(name = "tb_degrees")
@Getter
@Setter
public class Degree implements Serializable {
    private static final long serialVersionUID = 1344768769L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "degree_id")
    private Long degreeId;
    @Column(name = "degree_name")
    private String degreeName;

    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL)
    private Set<Course> courses = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private College college;


    public Degree() {
        //default constructor
    }

    public Degree(String name) {
        this.setDegreeName(name);
    }

    public Degree(Long id, String name, Set<Course> courses, College college) {
        this.setDegreeId(id);
        this.setDegreeName(name);
        this.setCourses(courses);
        this.setCollege(college);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Degree degree = (Degree) o;

        if (!Objects.equals(degreeId, degree.degreeId)) return false;
        if (!Objects.equals(degreeName, degree.degreeName)) return false;
        if (!Objects.equals(courses, degree.courses)) return false;
        return Objects.equals(college, degree.college);
    }

    @Override
    public int hashCode() {
        int result = degreeId != null ? degreeId.hashCode() : 0;
        result = 31 * result + (degreeName != null ? degreeName.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        result = 31 * result + (college != null ? college.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Degree{" +
                "degreeId=" + degreeId +
                ", degreeName='" + degreeName + '\'' +
                ", courses=" + courses +
                ", college=" + college +
                '}';
    }
}
