package ufp.esof.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        // insert into database (h2)
        System.out.println(degree.toString());
        return ResponseEntity.ok().build();
    }
}
