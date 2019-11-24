package ufp.esof.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
