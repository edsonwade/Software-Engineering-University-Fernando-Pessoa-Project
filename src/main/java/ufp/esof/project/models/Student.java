package ufp.esof.project.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = -1619636317041134369L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private Set<Appointment> appointments = new HashSet<>();

    public Student(String name) {
        this.setName(name);
    }

}
