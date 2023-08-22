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
public class Course implements Serializable {
    private static final long serialVersionUID = -2062070712995046595L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Explainer> explainers = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Degree degree;

    public Course(String name) {
        this.setName(name);
    }

    public void addExplainer(Explainer explainer) {
        this.explainers.add(explainer);
    }

}
