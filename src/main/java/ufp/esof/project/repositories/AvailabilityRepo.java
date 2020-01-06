package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.models.Availability;

@Repository
public interface AvailabilityRepo extends CrudRepository<Availability, Long> {
}
