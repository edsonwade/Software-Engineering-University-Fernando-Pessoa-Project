package ufp.esof.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.models.Degree;
import ufp.esof.project.repositories.DegreeRepo;

@Controller
@RequestMapping("/degree")
public class DegreeController {

    @Autowired
    private DegreeRepo degreeRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Degree>> getAllDegrees() {
        return ResponseEntity.ok(this.degreeRepo.findAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Degree> createDegree(@RequestBody Degree degree) {
        //TODO: move logic to service
        if (this.degreeRepo.findByName(degree.getName()).isPresent()) {
            throw new DegreeAlreadyExistsException(degree.getName());
        }

        return ResponseEntity.ok().build();
    }

    //TODO: move logic to service
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Client already exists")
    public static class DegreeAlreadyExistsException extends RuntimeException {
        public DegreeAlreadyExistsException(String name) {
            super("A degree with name \"" + name + "\" already exists");
        }
    }
}
