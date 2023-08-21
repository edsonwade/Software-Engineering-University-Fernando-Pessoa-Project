package ufp.esof.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.models.Appointment;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.models.Student;
import ufp.esof.project.repositories.AppointmentRepo;
import ufp.esof.project.repositories.ExplainerRepo;
import ufp.esof.project.repositories.StudentRepo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        currentInstance = new AppointmentServiceImpl(appointmentRepo, explainerRepo, studentRepo);
    }


    @Test
    void testReturnAllAppointment() {
        //given
        Set<Appointment> appointments = new HashSet<>();
        Appointment appointment = new Appointment(LocalDateTime.now(), LocalDateTime.now());
        appointments.add(appointment);
        // when
        when(appointmentRepo.findAll()).thenReturn(appointments);

        // then
        assertEquals(currentInstance.findAllAppointments(), appointments);
        assertEquals(1, appointments.size());

        //verify
        verify(appointmentRepo, atLeast(1)).findAll();

    }

    @Test
    void testFindByAppointementByIdThrowAnException() {
        when(Optional.of(appointmentRepo.findById((Long) anyLong()))).thenThrow(new ObjectNotFoundById(" the given id was not found"));
        assertThrows(ObjectNotFoundById.class, () -> currentInstance.findAppointmentById(1L));
        verify(this.appointmentRepo).findById((Long) any());

    }

    @Test
    void testFindByAppointementById() {
        Optional<Appointment> appointments = Optional.of(createAppointment().get(1));
        when(Optional.of(appointmentRepo.findById(1L))).thenReturn(Optional.of(appointments));
        Optional<Appointment> appointmentById = currentInstance.findAppointmentById(1L);

        assertFalse(appointmentById.isPresent());
        assertDoesNotThrow(() -> currentInstance.findAppointmentById(1L));

        verify(appointmentRepo,times(2)).findById((Long)any());


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

    /**
     * Method auxiliary for test appointments
     */
    public List<Appointment> createAppointment() {
        return List.of(new Appointment(1L, new Student("Vanilson"), new Explainer("Alexandro", "English")
                , LocalDateTime.now(), LocalDateTime.now()),
                new Appointment(223L, new Student("Sonia"), new Explainer("Rosa", "Spanish")
                        , LocalDateTime.now(), LocalDateTime.now()));
    }
}