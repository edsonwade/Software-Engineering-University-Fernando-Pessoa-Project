package ufp.esof.project.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "tb_explainers")
@Getter
@Setter
public class Explainer implements Serializable {

    private static final long serialVersionUID = -7007880810353385226L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "explainer_id",length = 11)
    @JsonProperty("id")
    private Long explainerId;
    @Column(name = "explainer_name")
    private String explainerName;
    @Column(unique = true, length = 40)
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();


    @JsonBackReference(value = "courses")
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Course> courses = new HashSet<>();


    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "explainer")
    private Set<Language> language = new HashSet<>();


    @JsonBackReference(value = "availabilities")
    @OneToMany(mappedBy = "explainer", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Availability> availabilities = new HashSet<>();

    public Explainer() {
        // default constructor
    }

    public Explainer(String name){
            this.setExplainerName(name);
    }

    @SuppressWarnings("unused")
    public Explainer(String name, String languages) {
        this.setExplainerName(name);
        this.setLanguage(language);
    }

    public Explainer(String name, Set<Course> courses) {
        this.setExplainerName(name);
        this.setCourses(courses);
    }

    public Explainer(Long explainerId, String explainerName, String email, Set<Course> courses) {
        this.explainerId = explainerId;
        this.explainerName = explainerName;
        this.email = email;
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Explainer explainer = (Explainer) o;

        if (!Objects.equals(explainerId, explainer.explainerId))
            return false;
        if (!Objects.equals(explainerName, explainer.explainerName))
            return false;
        if (!Objects.equals(email, explainer.email)) return false;
        if (!Objects.equals(appointments, explainer.appointments))
            return false;
        if (!Objects.equals(courses, explainer.courses)) return false;
        if (!Objects.equals(language, explainer.language)) return false;
        return Objects.equals(availabilities, explainer.availabilities);
    }

    @Override
    public int hashCode() {
        int result = explainerId != null ? explainerId.hashCode() : 0;
        result = 31 * result + (explainerName != null ? explainerName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (appointments != null ? appointments.hashCode() : 0);
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (availabilities != null ? availabilities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Explainer{" +
                "explainerId=" + explainerId +
                ", explainerName='" + explainerName + '\'' +
                ", email='" + email + '\'' +
                ", appointments=" + appointments +
                ", courses=" + courses +
                ", language=" + language +
                ", availabilities=" + availabilities +
                '}';
    }
}