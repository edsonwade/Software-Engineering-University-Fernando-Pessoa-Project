package ufp.esof.project.services;

import org.springframework.stereotype.Service;
import ufp.esof.project.persistence.model.Appointment;

import java.util.Optional;
import java.util.Set;

@Service
public interface AppointmentService {

    Set<Appointment> getSetAppointment();


    Iterable<Appointment> findAllAppointments();

    Optional<Appointment> findAppointmentById(Long id);

    Appointment createAppointment(Appointment appointment);


    boolean deleteById(Long id);

}