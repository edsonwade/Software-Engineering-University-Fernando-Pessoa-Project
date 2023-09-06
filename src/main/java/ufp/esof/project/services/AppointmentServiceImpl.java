package ufp.esof.project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.exception.ObjectNotFoundByName;
import ufp.esof.project.exception.RequiredObjectIsNullException;
import ufp.esof.project.persistence.model.Appointment;
import ufp.esof.project.persistence.repositories.AppointmentRepo;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Component
public class AppointmentServiceImpl implements AppointmentService {


    private final AppointmentRepo appointmentRepo;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    public static final String DOES_NOT_EXISTS = "does not exists";


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
    public Optional<Appointment> findAppointmentById(Long appointmentId) {
        Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
        if (appointment.isEmpty()) {
            logger.error("The appointment with id{}{}", appointmentId, DOES_NOT_EXISTS);
            throw new ObjectNotFoundById("appointment with id " + appointmentId + DOES_NOT_EXISTS);
        }
        return appointment;
    }


    @Override
    public Appointment createAppointment(@NotNull Appointment appointment) {
        logger.info("appointment created with success {}", appointment);
        return appointmentRepo.save(appointment);
    }

    @Override
    public Appointment updateAppointment(long appointmentId,Appointment appointment) {
        Optional<Appointment> degrees = appointmentRepo.findById(appointmentId);
        if (degrees.isEmpty()) {
            throw new ObjectNotFoundByName("appointment with name " + appointmentId + DOES_NOT_EXISTS);
        }
        if (Objects.isNull(appointment)){
            throw new RequiredObjectIsNullException();
        }
        Appointment newAppointment = new Appointment();
        newAppointment.setAppointmentId(appointment.getAppointmentId());
        newAppointment.setStudent(appointment.getStudent());
        newAppointment.setExplainer(appointment.getExplainer());
        newAppointment.setStartTime(LocalDateTime.now());
        newAppointment.setExpectedEndTime(LocalDateTime.now());
        return appointmentRepo.save(newAppointment);

    }

    @Override
    public boolean deleteById(Long appointmentId) {
        Optional<Appointment> appointmentOptional = this.findAppointmentById(appointmentId);
        if (appointmentOptional.isPresent()) {
            this.appointmentRepo.deleteById(appointmentId);
            return true;
        }
        return false;
    }

}