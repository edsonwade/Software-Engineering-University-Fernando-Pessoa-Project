package ufp.esof.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.models.Degree;
import ufp.esof.project.services.DegreeService;

import java.util.Optional;

@Controller
@RequestMapping("/degree")
public class DegreeController {

    private DegreeService degreeService;

    @Autowired
    public DegreeController(DegreeService degreeService) {
        this.degreeService = degreeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Degree>> getAllDegrees() {
        return ResponseEntity.ok(this.degreeService.findAllDegrees());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Degree> createDegree(@RequestBody Degree degree) {
        Optional<Degree> degreeOptional = this.degreeService.createDegree(degree);
        if (degreeOptional.isPresent()) {
            return ResponseEntity.ok(degreeOptional.get());
        }
        throw new DegreeNotCreatedException(degree.getName());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteDegree(@PathVariable("id") Long id) {
        boolean res = this.degreeService.deleteById(id);
        Optional<Degree> degreeOptional = degreeService.findById(id);
        if (degreeOptional.isPresent())
            throw new DegreeNotDeletedException(degreeOptional.get().getName());

        if (res)
            return ResponseEntity.ok("Degree deleted successfully!");
        else
            throw new InvalidDegreeException(id);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Degree not created")
    public static class DegreeNotCreatedException extends RuntimeException {
        public DegreeNotCreatedException(String name) {
            super("The degree with name \"" + name + "\" was not created");
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Degree not deleted")
    public static class DegreeNotDeletedException extends RuntimeException {
        public DegreeNotDeletedException(String name) {
            super("The degree with name \"" + name + "\" was not deleted");
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Degree")
    public static class InvalidDegreeException extends RuntimeException {
        public InvalidDegreeException(Long id) {
            super("The degree with id \"" + id + "\" does not exist");
        }
    }
}
