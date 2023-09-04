package ufp.esof.project.persistence.model;

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
@Getter
@Setter
@Table(name = "tb_students")
public class Student implements Serializable {
    private static final long serialVersionUID = -1619636317041134369L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", length = 11)
    @JsonProperty("id")
    private Long studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(unique = true, length = 40)
    @Email(message = "Email is not valid",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private Set<Appointment> appointments = new HashSet<>();

    public Student() {
        //default constructor
    }

    public Student(String name) {
        this.setStudentName(name);
    }

    public Student(Long studentId, String studentName, String email) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!Objects.equals(studentName, student.studentName)) return false;
        if (!Objects.equals(studentId, student.studentId)) return false;
        if (!Objects.equals(email, student.email)) return false;
        return Objects.equals(appointments, student.appointments);
    }

    @Override
    public int hashCode() {
        int result = studentName != null ? studentName.hashCode() : 0;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (appointments != null ? appointments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentId=" + studentId +
                ", email='" + email + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}
