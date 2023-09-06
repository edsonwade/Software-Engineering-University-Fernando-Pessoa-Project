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
import ufp.esof.project.persistence.model.Degree;
import ufp.esof.project.services.DegreeServiceImpl;
import ufp.esof.project.utils.MediaType;

import java.util.Optional;

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
        return  ResponseEntity.ok(degree);
    }

    @CrossOrigin(origins = {"http://localhost:8080",
            "http://localhost:8082"})
    @PostMapping(value = "/person/createNewPerson",
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
    public ResponseEntity<Degree> createDegree(@RequestBody Degree degree) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(degreeService.createDegree(degree));
    }
}
