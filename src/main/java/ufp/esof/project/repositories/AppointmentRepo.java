package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import ufp.esof.project.models.Appointment;

public interface AppointmentRepo extends CrudRepository<Appointment, Long> {
}
