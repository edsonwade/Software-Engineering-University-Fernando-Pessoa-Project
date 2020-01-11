package ufp.esof.project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonIgnore
    @ToString.Exclude
    @Enumerated
    private Language languages;

    @OneToMany(mappedBy = "explainer", cascade = CascadeType.PERSIST)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
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

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private Set<Student> students = new HashSet<>();

    @JsonInclude
    @ToString.Include
    public Set<String> courses() {
        Set<String> names = new HashSet<>();
        for (Course course : this.courses) {
            names.add(course.getName());
        }
        return names;
    }

    @JsonInclude
    @ToString.Include
    public Set<String> students() {
        Set<String> names = new HashSet<>();
        for (Student student : this.students) {
            names.add(student.getName());
        }
        return names;
    }

    public void update(Explainer explainer) {
        this.setStudents(explainer.getStudents());
        this.setCourses(explainer.getCourses());
    }

    public void addAvailabilityToExpaliner(Availability availability) {
        availabilities.add(availability);
        availability.setExplainer(this);

    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
        appointment.setExplainer(this);
        Student student = appointment.getStudent();
        if (student != null && !student.getAppointments().contains(appointment)) {
            student.addAppointment(appointment);
        }
    }

    public boolean canSchedule(Appointment appointment) {
        for (Availability availability : this.availabilities) {
            if (availability.contains(appointment)) {
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}