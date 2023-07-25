package ufp.esof.project.mockito.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ufp.esof.project.models.Appointment;
import ufp.esof.project.repositories.AppointmentRepo;
import ufp.esof.project.repositories.ExplainerRepo;
import ufp.esof.project.repositories.StudentRepo;
import ufp.esof.project.services.AppointmentServiceImpl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppointmentServiceImplTest {
    /**
     * The Appointment
     */
    public AppointmentRepo appointmentRepo;
    /**
     * The Explainer
     */
    public ExplainerRepo explainerRepo;
    /**
     * The  Student
     */
    public StudentRepo studentRepo;
    /**
     * AppointmentServiceImpl current instance
     */
    public AppointmentServiceImpl currentInstance;


    @BeforeEach
    void setUp() {
      appointmentRepo = mock(AppointmentRepo.class);
      studentRepo = mock(StudentRepo.class);
      studentRepo = mock(StudentRepo.class);
      explainerRepo = mock(ExplainerRepo.class);
      currentInstance = new AppointmentServiceImpl(appointmentRepo,explainerRepo,studentRepo);
    }

    @Test
    void getSetAppointment() {
        //given

        // when
        // then
    }

    @Test
    void testReturnAllAppointment() {
        //given
        Set<Appointment> appointments = new HashSet<>();
        Appointment appointment = new Appointment(LocalDateTime.now(),LocalDateTime.now());
        appointments.add(appointment);
        // when
        when(appointmentRepo.findAll()).thenReturn(appointments);

        // then
        assertEquals(currentInstance.findAllAppointments(),appointments);





    }

    @Test
    void testFindByAppointementById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findStudentById() {
    }

    @Test
    void save() {
    }

    @Test
    void saveAppointment() {
    }

    @Test
    void deleteById() {
    }
}