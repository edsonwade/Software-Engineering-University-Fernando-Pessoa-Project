package ufp.esof.project.services;

import org.springframework.stereotype.Component;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.persistence.model.Appointment;
import ufp.esof.project.persistence.repositories.AppointmentRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    public static final String NOT_FOUND = "not found";

    private final AppointmentRepo appointmentRepo;


    public AppointmentServiceImpl(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Set<Appointment> getSetAppointment() {
        Set<Appointment> appointments = new HashSet<>();
        for (Appointment appointment : this.appointmentRepo.findAll()) {
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public Iterable<Appointment> findAllAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public Optional<Appointment> findAppointmentById(Long id) {
        return Optional.of(appointmentRepo.findById(id))
                .orElseThrow(() -> new ObjectNotFoundById( "Appointment with " + id + NOT_FOUND));
    }


    @Override
    public Appointment createAppointment(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Appointment> appointmentOptional = this.findAppointmentById(id);
        if (appointmentOptional.isPresent()) {
            this.appointmentRepo.deleteById(id);
            return true;
        }
        return false;
    }

}