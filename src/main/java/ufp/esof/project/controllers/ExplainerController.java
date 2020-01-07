package ufp.esof.project.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.filters.FilterObject;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.repositories.ExplainerRepo;
import ufp.esof.project.services.ExplainerService;

import java.util.Optional;
import java.util.Set;

@Controller
@RestController
@RequestMapping("/explainer")
public class ExplainerController {


    private Logger logger = LoggerFactory.getLogger(ExplainerController.class);

    private ExplainerService explainerServiceImpl;

    private ExplainerRepo explainerRepo;

    @Autowired
    public ExplainerController(@Qualifier("explainerServiceImpl") ExplainerService explainerServiceImpl) {
        this.explainerServiceImpl = explainerServiceImpl;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Set<Explainer> getAllExplainer(@ModelAttribute FilterObject filterObject) {
        logger.info(filterObject.toString());
        return explainerServiceImpl.getFilteredExplainer(filterObject);

    }


    // mapeando o id
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Explainer getById(@PathVariable("id") Long id) {
        Optional<Explainer> explainerOptional = explainerServiceImpl.getById(id);


        return explainerOptional.orElse(null);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explainer> createDegree(@RequestBody Explainer explainer) {
        //TODO: move logic to service
        if (this.explainerServiceImpl.findExplainerByName(explainer.getName()).isPresent()) {
            throw new ExplainerController.ExplainerAlreadyExistsException(explainer.getName());
        }

        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Explainer> updateExpaliner(@RequestBody Explainer explainer, @PathVariable("id") Long id) {

        Optional<Explainer> explainerOptional = explainerServiceImpl.getById(id);

        if (!explainerOptional.isPresent()) return ResponseEntity.notFound().build();

        explainer.setId(id);

        explainerServiceImpl.save(explainer);

        return ResponseEntity.ok().build();
    }


    //TODO: move logic to service
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Client already exists")
    public static class ExplainerAlreadyExistsException extends RuntimeException {
        public ExplainerAlreadyExistsException(String name) {
            super("A degree with name \"" + name + "\" already exists");
        }
    }



}

