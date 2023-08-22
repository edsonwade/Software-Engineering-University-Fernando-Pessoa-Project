package ufp.esof.project.services;

import org.springframework.stereotype.Component;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.models.Appointment;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.models.Student;
import ufp.esof.project.repositories.AppointmentRepo;
import ufp.esof.project.repositories.ExplainerRepo;
import ufp.esof.project.repositories.StudentRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class AppointmentServiceImpl implements AppointmentService {

    public static final String NOT_FOUND = "not found";

    private final AppointmentRepo appointmentRepo;

    private final ExplainerRepo explainerRepo;

    private final StudentRepo studentRepo;



    public AppointmentServiceImpl(AppointmentRepo appointmentRepo, ExplainerRepo explainerRepo, StudentRepo studentRepo) {
        this.appointmentRepo = appointmentRepo;
        this.explainerRepo = explainerRepo;
        this.studentRepo = studentRepo;

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
    public Optional<Explainer> findByExplainerByName(String nameExplainer) {
        return Optional.of(explainerRepo.findByName(nameExplainer))
                .orElseThrow(() -> new ObjectNotFoundById("Explainer with name " + nameExplainer + NOT_FOUND));
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        return Optional.of(studentRepo.findById(id))
                .orElseThrow(() -> new ObjectNotFoundById(" Student with " + id + NOT_FOUND));

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