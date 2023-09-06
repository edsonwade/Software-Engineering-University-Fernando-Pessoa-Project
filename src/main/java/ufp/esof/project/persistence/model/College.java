package ufp.esof.project.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_colleges")
@Getter
@Setter
public class College implements Serializable {

    private static final long serialVersionUID = 6789185811908698013L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Long collegeId;

    private String name;

    @OneToMany(mappedBy = "college", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Set<Degree> degrees = new HashSet<>();

    public College() {
        // default constructor
    }

    public College(String name) {
        this.setName(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        College college = (College) o;

        if (!Objects.equals(collegeId, college.collegeId)) return false;
        if (!Objects.equals(name, college.name)) return false;
        return Objects.equals(degrees, college.degrees);
    }

    @Override
    public int hashCode() {
        int result = collegeId != null ? collegeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (degrees != null ? degrees.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "College{" +
                "collegeId=" + collegeId +
                ", name='" + name + '\'' +
                ", degrees=" + degrees +
                '}';
    }
}
