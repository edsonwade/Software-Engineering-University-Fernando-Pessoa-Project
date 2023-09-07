package ufp.esof.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
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
    @DisplayName("GET /api/v1/appointment/1 - Success")
    void testGetAppointmentByIdFound() throws Exception {
        when(appointmentServiceMock.findAppointmentById(1L))
                .thenReturn(Optional.of(appointment));
        // Execute the Get request
        this.mockMvc
                .perform(get("/api/v1/appointment/{id}",1))
                //Validate the response code and content
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate the return fields
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$.id").value(1L));

        verify(appointmentServiceMock, times(1)).findAppointmentById(1L);

    }

    @Test
    @DisplayName("GET /api/v1/appointment/1 - Not Found")
    void testGetAppointmentByIdNotFound() throws Exception {
        when(appointmentServiceMock.findAppointmentById(1L))
                .thenThrow(new ObjectNotFoundById("appointment not found"));

        // Act: Execute the GET request
        this.mockMvc
                .perform(get("/api/v1/appointment/{id}", 1L)) // Assuming you're testing with ID 1
                // Assert: Validate the response code
                .andExpect(status().isNotFound());
    }
    // TODO: 07/09/2023 - need to fix the create appointment test 
//    @Test
//    @DisplayName("POST /api/v1/appointment/create - Success")
//    void testPostSaveAppointmentSuccess() throws Exception {
//        Appointment appointment = new Appointment();
//        appointment.setAppointmentId(1L);
//        appointment.setStudent(new Student("Vanilson"));
//        appointment.setExplainer(new Explainer("Alexandro", "English"));
//        appointment.setStartTime(LocalDateTime.now());
//        appointment.setExpectedEndTime(LocalDateTime.now().plusHours(1));
//
//        // Mock the appointment creation in the service layer
//        when(appointmentServiceMock.createAppointment(appointment)).thenReturn(appointment);
//
//        // Perform the POST request with the appointment as JSON
//        this.mockMvc
//                .perform(post("/api/v1/appointment/create")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON) // Set the Content-Type header
//                        .content(asJsonString(appointment))) // JSON data
//                .andExpect(status().isCreated());
//
//    }

    @Test
    @DisplayName("DELETE /api/v1/appointment/delete/{id} - Success")
    void testDeleteAppointmentByFound() throws Exception {
        when(appointmentServiceMock.deleteById(anyLong())).thenReturn(true);
        // Execute the Get request
        this.mockMvc
                .perform(delete("/api/v1/appointment/delete/{id}",1))
                //Validate the response code
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/json")));

        verify(appointmentServiceMock, times(1)).deleteById(1L);
    }
    @Test
    @DisplayName("DELETE /api/v1/appointment/delete/1 - false")
    void testDeleteAppointmentByIdNotFound() throws Exception {
        // Arrange: Configure the appointmentServiceMock to throw ObjectNotFoundById exception
        when(appointmentServiceMock.deleteById(1L)).thenReturn(false);

        // Act: Execute the DELETE request
        this.mockMvc
                .perform(delete("/api/v1/appointment/delete/{id}", 1L))
                // Assert: Validate the response code is 404
                .andExpect(status().isNotFound())
                .andExpect(content().string("Appointment not found"));

        // Verify that the service method was called with the correct ID
        verify(appointmentServiceMock, times(1)).deleteById(1L);
    }

    // Helper method to convert an object to JSON string
    private String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}