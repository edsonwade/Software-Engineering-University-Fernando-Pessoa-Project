package ufp.esof.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.persistence.model.Appointment;
import ufp.esof.project.persistence.model.Explainer;
import ufp.esof.project.persistence.model.Student;
import ufp.esof.project.services.AppointmentServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {
    @MockBean
    AppointmentServiceImpl appointmentServiceMock;
    @Autowired
    private MockMvc mockMvc;
    /**
     * Appointment instance
     */
    Appointment appointment;


    @BeforeEach
    void setUp() {
        appointment = new Appointment(1L,
                new Student("Vanilson"),
                new Explainer("Alexandro", "English")
                , LocalDateTime.now(),
                LocalDateTime.now());

    }


    @Test
    @DisplayName("GET /api/v1/appointment - Return All")
    void testGetAllAppointments() throws Exception {
        when(appointmentServiceMock.findAllAppointments())
                .thenReturn(List.of(new Appointment(1L,
                        new Student("Vanilson"),
                        new Explainer("Alexandro", "English")
                        , LocalDateTime.now(),
                        LocalDateTime.now())));
        // Execute the Get request
        this.mockMvc
                .perform(get("/api/v1/appointment"))
                //Validate the response code and content
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                //Validate the return fields
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L));

    }


    @Test
    @DisplayName("GET /api/v1/appointment/1 - Found")
    void testGetAppointmentByIdFound() throws Exception {
        when(appointmentServiceMock.findAppointmentById(1L))
                .thenReturn(Optional.of(appointment));
        // Execute the Get request
        this.mockMvc
                .perform(get("/api/v1/appointment/1"))
                //Validate the response code and content
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate the return fields
                .andExpect(jsonPath("$.size()").value(5))
                .andExpect(jsonPath("$.id").value(1L));

    }

    @Test
    @DisplayName("GET /api/v1/appointment/1 - Not Found")
    void testGetAppointmentByIdNotFound() throws Exception {
        when(appointmentServiceMock.findAppointmentById(anyLong()))
                .thenThrow(new ObjectNotFoundById(" the given id was not found"));
        // Execute the Get request
        this.mockMvc
                .perform(get("/api/v1/appointment/1"))
                //Validate the response code
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v1/appointment/create - create new Appointment")
    void testPostCreateAppointment() throws Exception {
        Appointment appointments = new Appointment(
                LocalDateTime.now(),
                LocalDateTime.now());

        when(appointmentServiceMock.createAppointment(appointments))
                .thenReturn(appointments);

        this.mockMvc
                .perform(post("/api/v1/appointment/create")
                        .contentType(asJsonString(appointments))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1L));


    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}