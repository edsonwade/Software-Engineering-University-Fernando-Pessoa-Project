package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import ufp.esof.project.models.Availability;

public interface AvailabilityRepo extends CrudRepository<Availability, Long> {
}
