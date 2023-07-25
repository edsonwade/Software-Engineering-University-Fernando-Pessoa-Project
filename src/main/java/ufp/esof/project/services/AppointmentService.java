package ufp.esof.project.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Appointment;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.models.Student;

import java.util.Optional;
import java.util.Set;

@Service
@Repository

public interface AppointmentService {

    Set<Appointment> getSetAppointment();


    Iterable<Appointment> findAllAppointments();

    Optional<Appointment> findAppointmentById(Long id);

    Optional<Explainer> findByExplainerByName(String nameExplainer);

    Optional<Student> findStudentById(Long id);

    Appointment createAppointment(Appointment appointment);


    boolean deleteById(Long id);

}