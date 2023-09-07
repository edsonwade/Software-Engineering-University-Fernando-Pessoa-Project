package ufp.esof.project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.exception.ObjectNotFoundByName;
import ufp.esof.project.persistence.model.Degree;
import ufp.esof.project.persistence.repositories.CourseRepo;
import ufp.esof.project.persistence.repositories.DegreeRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DegreeServiceImplTest {


    public static final String EXPECTED_MESSAGE = "Degree with id 0 does not exists";
    public DegreeRepo degreeRepoMock;

    public CourseRepo courseMock;
    public CollegeService collegeMock;

    public DegreeServiceImpl currentInstance;
    /**
     * The Degree
     */
    Degree degree;

    @BeforeEach
    void setUp() {
        degreeRepoMock = mock(DegreeRepo.class);
        courseMock = mock(CourseRepo.class);
        collegeMock = mock(CollegeService.class);
        currentInstance = new DegreeServiceImpl(degreeRepoMock, collegeMock, courseMock);

        degree = new Degree(1L, "Engineering Technology");
    }

    @Test
    @DisplayName("list all degrees")
    void test_shouldReturnAllDegrees() {
        //arrange
        List<Degree> degrees = createDegree();
        // when
        when(degreeRepoMock.findAll()).thenReturn(degrees);
        //then
        assertEquals(3, degrees.size());
        assertEquals(currentInstance.findAllDegree(), degrees);

        verify(degreeRepoMock, times(1)).findAll();


    }

    @Test
    @DisplayName("Return degree by id - Found")
    void test_shouldReturnDegreeWhenTheGivenIdIsFound() {
        //arrange
        Optional<Degree> degrees = Optional.of(degree);
        // when
        when(degreeRepoMock.findById(anyLong())).thenReturn(degrees);
        //then
        assertEquals(currentInstance.findDegreeById(anyLong()), degrees);
        assertFalse(currentInstance.findDegreeById(anyLong()).isEmpty());
        assertDoesNotThrow(() -> currentInstance.findDegreeById(anyLong()));

        verify(degreeRepoMock, times(3)).findById(anyLong());
    }


    @Test
    @DisplayName("Throws exception , when the given id  is not found -Not Found")
    void test_shouldThrownAnExceptionWhenTheGivenIdIsNotFound() {
        when(Optional.of(degreeRepoMock.findById(anyLong()))).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundById.class, () -> currentInstance.findDegreeById(1L));
        verify(degreeRepoMock).findById(any());

    }

    @Test
    @DisplayName("Return degree by name - Found")
    void test_shouldReturnDegreeWhenTheGivenNameIsFound() {
        //arrange
        Optional<Degree> degrees = Optional.of(degree);
        // when
        when(degreeRepoMock.findByDegreeName(anyString())).thenReturn(degrees);
        //then
        assertEquals(currentInstance.findDegreeByName(anyString()), degrees);
        assertFalse(currentInstance.findDegreeByName(anyString()).isEmpty());
        assertDoesNotThrow(() -> currentInstance.findDegreeByName(anyString()));

        verify(degreeRepoMock, times(3)).findByDegreeName(anyString());
    }

    @Test
    @DisplayName("Throws exception , when the given name  is not found -Not Found")
    void test_shouldThrownAnExceptionWhenTheGivenNameIsNotFound() {
        when(Optional.of(degreeRepoMock.findByDegreeName(anyString()))).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundByName.class, () -> currentInstance.findDegreeByName(anyString()));
        verify(degreeRepoMock).findByDegreeName(anyString());

    }

    @Test
    @DisplayName("Create New Degree")
    void test_shouldCreateNewDegree() {
        Degree degrees = new Degree(4L, "Medicine");
        when(degreeRepoMock.save(degrees)).thenReturn(degrees);
        assertNotEquals(currentInstance.createDegree(degrees), degree);
        assertNotEquals(1L,currentInstance.createDegree(degrees).getDegreeId());
        verify(degreeRepoMock, times(2)).save(degrees);
        verify(degreeRepoMock,atLeastOnce()).save(degrees);
    }


    @Test
    @DisplayName("given an specific id ,delete degree ")
    void testDeleteAppointmentByIdAndReturnTrue() {
        // when
        when(degreeRepoMock.existsById(1L)).thenReturn(true);
        currentInstance.deleteDegree(1L);
        verify(degreeRepoMock,times(1)).deleteById(degree.getDegreeId());

    }

    @Test
    @DisplayName("given an specific id , return false , when the given id is not found")
    void testDeleteAppointmentByIdReturnFalseWhenTheIdIsNotFound() {
        when((degreeRepoMock.existsById(anyLong()))).thenReturn(false);
        ObjectNotFoundById objectNotFoundById = assertThrows(
                ObjectNotFoundById.class,
                () -> currentInstance.deleteDegree(anyLong()));
        assertEquals(EXPECTED_MESSAGE,objectNotFoundById.getMessage());
        verify(degreeRepoMock,times(1)).existsById(anyLong());


   }
    /**
     * Auxiliary method for test
     *
     * @return list of degree
     */
    public List<Degree> createDegree() {
        return List.of(
                new Degree(1L, "Engineering Technology"),
                new Degree(2L, "Architecture"),
                new Degree(3L, "Engineering Technology")

        );
    }
}