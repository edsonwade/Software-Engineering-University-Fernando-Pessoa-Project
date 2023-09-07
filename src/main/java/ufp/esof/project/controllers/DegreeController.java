package ufp.esof.project.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.exception.ObjectNotFoundByName;
import ufp.esof.project.persistence.model.Degree;
import ufp.esof.project.services.DegreeServiceImpl;
import ufp.esof.project.utils.MediaType;

import javax.validation.Valid;
import java.util.Optional;

import static ufp.esof.project.services.DegreeServiceImpl.*;

@RestController
@RequestMapping("/api/v1/degree")
@Tag(name = "Degree", description = "Endpoints for Managing Degree")
public class DegreeController {

    private final DegreeServiceImpl degreeService;

    public DegreeController(DegreeServiceImpl degreeService) {
        this.degreeService = degreeService;
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            })
    @Operation(summary = "Find All Degree", description = "Find All Degree",
            tags = {"Degree"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Degree.class))
                            )}),

                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Iterable<Degree>> getAllDegrees() throws IllegalAccessException {
        return ResponseEntity.ok(Optional.of(degreeService.findAllDegree())
                .orElseThrow(IllegalAccessException::new));
    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8082"})

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            }
    )
    @Operation(summary = "Find Degree By Id", description = "Find  Degree By Id",
            tags= {"Degree"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = Degree.class), mediaType = "application/json")),
                    @ApiResponse(description = "No Content"
                            , responseCode = "204"
                            , content = @Content),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Optional<Degree>> getDegreeById(@PathVariable("id") Long id) {
        Optional<Degree> degree = degreeService.findDegreeById(id);
         if(degree.isEmpty()){
             throw new ObjectNotFoundById(DEGREE_WITH_ID + id + DOES_NOT_EXISTS);
         }
         return ResponseEntity.ok(degree);
    }

    @GetMapping(value = "/findByName/{degreeName}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML
            })
    @Operation(summary = "Find Degree By Name",
            description = "Find Degree By Name",
            tags = {"Degree"},
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Degree.class), mediaType = "application/json")),
                    @ApiResponse(description = "No Content",
                            responseCode = "204",
                            content = @Content),
                    @ApiResponse(description = "Bad Request",
                            responseCode = "400",
                            content = @Content),
                    @ApiResponse(description = "Unauthorized",
                            responseCode = "401",
                            content = @Content),
                    @ApiResponse(description = "Not Found",
                            responseCode = "404",
                            content = @Content),
                    @ApiResponse(description = "Internal Error",
                            responseCode = "500",
                            content = @Content)
            })
    public ResponseEntity<Optional<Degree>> findDegreeByName(@PathVariable("degreeName") String degreeName) {
        Optional<Degree> degree = degreeService.findDegreeByName(degreeName);
        if (degree.isEmpty()) {
            throw new ObjectNotFoundByName(DEGREE_WITH_NAME + degreeName + DOES_NOT_EXISTS);
        }
        return ResponseEntity.ok(degree);
    }


    @CrossOrigin(origins = {"http://localhost:8080",
            "http://localhost:8082"})
    @PostMapping(value = "/createNewDegree",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Add  a new  Degree by passing" +
            "in Json ,XML, OR YAML representation of Degree ",
            description = "Add  a new  Degree",
            tags = {"Degree"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "201"
                            , content = @Content(schema = @Schema(implementation = Degree.class), mediaType = "application/json")),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Degree> createDegree(@Valid @RequestBody Degree degree) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(degreeService.createDegree(degree));
    }

    @PutMapping(value = "/update/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Update a Degree by passing" +
            "in Json ,XML, OR YAML representation of Degree ",
            description = "Updated",
            tags = {"Degree"},
            responses = {
                    @ApiResponse(description = "Success"
                            , responseCode = "200"
                            , content = @Content(schema = @Schema(implementation = Degree.class), mediaType = "application/json")),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Degree> updateDegree(@PathVariable(value = "id") Integer id, @Valid @RequestBody Degree degree) {
        Optional<Degree> degrees = degreeService.findDegreeById(id);
        if(degrees.isEmpty()) {
            throw new ObjectNotFoundById(DEGREE_WITH_ID + id + DOES_NOT_EXISTS);
        }
        return ResponseEntity.ok().body(degreeService.updateDegree(id,degree));

    }
    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Delete a Degree by Id",
            description = "Delete",
            tags = {"Degree"},
            responses = {
                    @ApiResponse(description = "No Content"
                            , responseCode = "204"
                            , content = @Content),
                    @ApiResponse(description = "Bad Request"
                            , responseCode = "400"
                            , content = @Content),
                    @ApiResponse(description = "Unauthorized"
                            , responseCode = "401"
                            , content = @Content),
                    @ApiResponse(description = "Not Found"
                            , responseCode = "404"
                            , content = @Content),
                    @ApiResponse(description = "Internal Error"
                            , responseCode = "500"
                            , content = @Content)
            })
    public ResponseEntity<Object> deleteParkingSpotById(@PathVariable(value = "id") int id) {
        Optional<Degree> degree = degreeService.findDegreeById(id);
        if(degree.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DEGREE_WITH_ID + id + " was  not found");
        }
         degreeService.deleteDegree(id);
        return ResponseEntity.status(HttpStatus.OK).body(DEGREE_WITH_ID + id + " deleted successfully ");
    }
}



