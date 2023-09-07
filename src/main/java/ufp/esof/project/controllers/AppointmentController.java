package ufp.esof.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.persistence.model.Appointment;
import ufp.esof.project.services.AppointmentServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/appointment")
public class AppointmentController {


    private final AppointmentServiceImpl appointmentService;


    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Appointment>> getAllAppointment() {
        return ResponseEntity.ok(this.appointmentService.findAllAppointments());
    }


    @GetMapping(value = "/{id}")
    public  ResponseEntity<Optional<Appointment>> getById(@PathVariable("id") Long id) {
        try {
            Optional<Appointment> appointment = appointmentService.findAppointmentById(id);
            return ResponseEntity.ok(appointment);
        } catch (ObjectNotFoundById exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
          return ResponseEntity.status(HttpStatus.CREATED)
                  .body(appointmentService.createAppointment(appointment));

    }
    @DeleteMapping(value = "/delete/{id}")
        public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id) {
        boolean deleted = appointmentService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Appointment Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment not found");
        }

    }
}

