package ufp.esof.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.persistence.model.Availability;

import java.util.Optional;

@Repository
public interface AvailabilityRepo extends CrudRepository<Availability, Long> {
    Optional<Availability> findById(Long id);

}
