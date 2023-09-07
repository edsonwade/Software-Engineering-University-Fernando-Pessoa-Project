package ufp.esof.project.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ufp.esof.project.exception.CustomJsonSerializationException;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.exception.ObjectNotFoundByName;
import ufp.esof.project.persistence.model.College;
import ufp.esof.project.persistence.model.Degree;
import ufp.esof.project.services.DegreeServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DegreeController.class)
class DegreeControllerTest {

    @MockBean
    DegreeServiceImpl degreeServiceMock;
    @Autowired
    private MockMvc mockMvc;

    Degree degree;

    @BeforeEach
    void setUp() {
        degree = new Degree(1L, "Data Analytic", new HashSet<>(), new College("Oxford University"));
    }

    @Test
    @DisplayName("GET /api/v1/degree - Success")
    void testGetAllDegrees() throws Exception {
        when(degreeServiceMock.findAllDegree())
                .thenReturn(List.of(
                        new Degree(1L, "Data Analytic", new HashSet<>(), new College("Oxford University")),
                        new Degree(234L, "AI Scientific", new HashSet<>(), new College("University Fernando Pessoa"))
                ));
        this.mockMvc
                .perform(get("/api/v1/degree"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].degreeId").value(1L))
                .andExpect(jsonPath("$[0].degreeName").value("Data Analytic"));

        verify(degreeServiceMock, times(1)).findAllDegree();

    }

    @Test
    @DisplayName("GET /api/v1/degree/1 - Success")
    void testGetAppointmentByIdFound() throws Exception {
        when(degreeServiceMock.findDegreeById(1L))
                .thenReturn(Optional.of(degree));
        // Execute the Get request
        this.mockMvc
                .perform(get("/api/v1/degree/{id}", 1))
                //Validate the response code and content
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                //Validate the return fields
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.degreeId").value(1L))
                .andExpect(jsonPath("$.degreeName").value("Data Analytic"));

        verify(degreeServiceMock, times(1)).findDegreeById(1L);

    }

    @Test
    @DisplayName("GET /api/v1/degree/1 - Not Found")
    void testGetAppointmentByIdNotFound() throws Exception {
        when(degreeServiceMock.findDegreeById(1L))
                .thenThrow(new ObjectNotFoundById("Degree not found"));
        mockMvc.perform(
                        get("/api/v1/degree/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/v1/degree/findByName/{degreeName} - Found")
    void testFindDegreeByNameFound() throws Exception {
        String degreeName = "Engineering Technology";
        Degree degree = new Degree(1L, degreeName);

        when(degreeServiceMock.findDegreeByName(degreeName)).thenReturn(Optional.of(degree));

        mockMvc.perform(get("/api/v1/degree/findByName/{degreeName}", degreeName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.degreeId", is(1)))
                .andExpect(jsonPath("$.degreeName", is(degreeName)));
    }

    @Test
    @DisplayName("GET /api/v1/degree/findByName/{degreeName} - Not Found")
    void testFindDegreeByNameNotFound() throws Exception {
        String degreeName = "Nonexistent Degree";

        when(degreeServiceMock.findDegreeByName(degreeName)).thenThrow(new ObjectNotFoundByName("degree with name " + degreeName + "not found"));

        mockMvc.perform(get("/api/v1/degree/findByName/{degreeName}", degreeName))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v1/degree/create - Success")
    void testPostSaveAppointmentSuccess() throws Exception {
        Degree degrees = createDegree();

        when(degreeServiceMock.createDegree(degrees)).thenReturn(degrees);

        this.mockMvc
                .perform(post("/api/v1/degree/createNewDegree")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON) // Set the Content-Type header
                        .content(asJsonString(degrees))) // JSON data
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.degreeId").value(123L))
                .andExpect(jsonPath("$.degreeName").value("Data Analytic"));


    }


    @Test
    @DisplayName("POST /api/v1/degree/create - Null Object")
    void testPostSaveNullDegree() throws Exception {
        when(degreeServiceMock.createDegree(null)).thenReturn(null);
        this.mockMvc
                .perform(post("/api/v1/degree/createNewDegree")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null)))
                .andExpect(status().isBadRequest());
    }



    @Test
    @DisplayName("PUT/api/v1/degree/update/{id} - Success")
    void testUpdateDegreeSuccess() throws Exception {
        String updatedDegreeJson = "{\n" +
                "    \"degreeId\": 1,\n" +
                "    \"degreeName\": \"Updated Degree Name\"\n" +
                "}";
        Degree updatedDegree = new Degree(1L, "Updated Degree Name");
        when(degreeServiceMock.findDegreeById(1L)).thenReturn(Optional.of(degree));
        when(degreeServiceMock.updateDegree(1L, updatedDegree)).thenReturn(updatedDegree);

        mockMvc.perform(put("/api/v1/degree/update/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDegreeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.degreeId").value(1))
                .andExpect(jsonPath("$.degreeName").value("Updated Degree Name"));
    }

    @Test
    @DisplayName("PUT/api/v1/degree/update/{id} - Not Found")
 void testUpdateDegreeNotFound() throws Exception {
        String updatedDegreeJson = "{\n" +
                "    \"degreeId\": 1,\n" +
                "    \"degreeName\": \"Updated Degree Name\"\n" +
                "}";
        when(degreeServiceMock.findDegreeById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/degree/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedDegreeJson))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("DELETE /api/v1/degree/delete/1 - Success")
    void testDeleteDegreeByIdFound() throws Exception {
        long degreeId = 1L;
        when(degreeServiceMock.findDegreeById(degreeId)).thenReturn(Optional.of(degree));

        mockMvc.perform(delete("/api/v1/degree/delete/{id}", degreeId))
                .andExpect(status().isOk())
                .andExpect(content().string("degree with id " + degreeId + " deleted successfully "));

        verify(degreeServiceMock, times(1)).deleteDegree(degreeId);
        verify(degreeServiceMock, times(1)).findDegreeById(degreeId);
    }

    @Test
    @DisplayName("DELETE /api/v1/degree/delete/1 - Degree Not Found")
    void testDeleteDegreeByIdNotFound() throws Exception {
        long degreeId = 12345L;
        when(degreeServiceMock.findDegreeById(degreeId)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/v1/degree/delete/{id}", degreeId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("degree with id " + degreeId + " was  not found"));

        verify(degreeServiceMock, never()).deleteDegree(degreeId);
        verify(degreeServiceMock, times(1)).findDegreeById(degreeId);
    }


    /**
     * Auxiliary method for test
     *
     * @return degree
     */
    private static Degree createDegree() {
        Degree degree = new Degree();
        degree.setDegreeId(123L);
        degree.setDegreeName("Data Analytic");
        return degree;
    }

    /**
     * Helper method to convert an object to JSON string
     *
     * @param obj object
     * @return obj
     */
    protected String asJsonString(final Object obj) throws CustomJsonSerializationException {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new CustomJsonSerializationException("Failed to serialize object to JSON", e);
        }
    }


}