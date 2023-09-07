package ufp.esof.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.persistence.model.Appointment;
import ufp.esof.project.persistence.model.Explainer;
import ufp.esof.project.persistence.model.Student;
import ufp.esof.project.persistence.repositories.AppointmentRepo;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {
    public static final String DOES_NOT_EXISTS = "appointment with id 0 does not exists";
    /**
     * The Appointment
     */
    public AppointmentRepo appointmentRepo;
    public AppointmentServiceImpl currentInstance;

    Appointment appointment;


    @BeforeEach
    void setUp() {
        appointmentRepo = mock(AppointmentRepo.class);
        currentInstance = new AppointmentServiceImpl(appointmentRepo);
        appointment = new Appointment(1L, new Student("Vanilson"), new Explainer("Alexandro", "English")
                , LocalDateTime.now(), LocalDateTime.now());
    }


    @Test
    @DisplayName("List All Appointments")
    void testReturnListOfAllAppointments() {
        //given
        Set<Appointment> appointments = new HashSet<>();
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
    @DisplayName("set new appointments")
     void testGetSetAppointment() {
        List<Appointment> appointments = Collections.singletonList(appointment);
        Set<Appointment> expectedAppointments = new HashSet<>(appointments);
        when(appointmentRepo.findAll()).thenReturn(expectedAppointments);
        Set<Appointment> actualAppointments = currentInstance.getSetAppointment();
        assertEquals(expectedAppointments, actualAppointments);
        verify(appointmentRepo,times(1)).findAll();
    }

    @Test
    @DisplayName("given a specific id and return the appointment")
    void testFindAppointementById() {
        Optional<Appointment> appointments = Optional.of(appointment);
        when(appointmentRepo.findById(anyLong())).thenReturn(appointments);
        assertEquals(currentInstance.findAppointmentById(anyLong()), appointments);
        assertFalse(currentInstance.findAppointmentById(anyLong()).isEmpty());
        assertDoesNotThrow(() -> currentInstance.findAppointmentById(1L));

        verify(appointmentRepo,times(3)).findById(anyLong());

    }

    @Test
    @DisplayName("Throws exception , when the given id  is not found")
    void testFindAppointementByIdAndThrowsAnException() {
        when(Optional.of(appointmentRepo.findById(anyLong()))).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundById.class, () -> currentInstance.findAppointmentById(anyLong()));
        verify(this.appointmentRepo).findById( anyLong());

    }


    @Test
    @DisplayName("create a new appointment")
    void testCreateNewAppointment() {
        Appointment appointments = new Appointment(2L, new Student("Sonia"), new Explainer("Rosa", "Portuguese")
                , LocalDateTime.now(), LocalDateTime.now());
        when(appointmentRepo.save(appointments)).thenReturn(appointments);
        assertNotEquals(currentInstance.createAppointment(appointments),appointment);
        verify(appointmentRepo,times(1)).save(appointments);

    }

    @Test
    @DisplayName("given an specific id ,delete appointment and return true ")
    void testDeleteAppointmentByIdAndReturnTrue() {
        given(appointmentRepo.findById(anyLong())).willReturn(Optional.of(appointment));
        doNothing().when(appointmentRepo).deleteById(anyLong());
        currentInstance.deleteById(anyLong());
        verify(appointmentRepo,times(1)).deleteById(anyLong());

    }

    @Test
    @DisplayName("given an specific id , return false , when the given id is not found")
    void testDeleteAppointmentByIdReturnFalseWhenTheIdIsNotFound() {
        when(appointmentRepo.findById(anyLong())).thenReturn(Optional.empty());
        doNothing().when(appointmentRepo).deleteById(anyLong());
        ObjectNotFoundById objectNotFoundById = assertThrows(
                ObjectNotFoundById.class,
                () -> currentInstance.deleteById(anyLong()));
        assertEquals(DOES_NOT_EXISTS,objectNotFoundById.getMessage());
        verify(appointmentRepo,times(0)).deleteById(anyLong());

    }


    /**
     * Method auxiliary for test appointments
     */
    public List<Appointment> createListOfAppointments() {
        return List.of(new Appointment(1L, new Student("Vanilson"), new Explainer("Alexandro", "English")
                , LocalDateTime.now(), LocalDateTime.now()),
                new Appointment(223L, new Student("Sonia"), new Explainer("Rosa", "Spanish")
                        , LocalDateTime.now(), LocalDateTime.now()));
    }


}