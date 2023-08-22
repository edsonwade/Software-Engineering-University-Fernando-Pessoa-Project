package ufp.esof.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufp.esof.project.models.Appointment;
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
        Optional<Appointment> appointmentOptional = appointmentService.findAppointmentById(id);
        return ResponseEntity.ok(appointmentOptional);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Appointment> saveAppointment(@RequestBody Appointment appointment) {
          return ResponseEntity.status(HttpStatus.CREATED)
                  .body(appointmentService.createAppointment(appointment));

    }
    @DeleteMapping(value = "/delete/{id}")
        public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id) {
        return ResponseEntity.ok( this.appointmentService.deleteById(id) + " Appointment Deleted Successfully" );

    }
}

