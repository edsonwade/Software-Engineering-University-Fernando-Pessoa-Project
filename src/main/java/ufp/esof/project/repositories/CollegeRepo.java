package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import ufp.esof.project.models.College;

import java.util.Optional;

public interface CollegeRepo extends CrudRepository<College, Long> {
    Optional<College> findByName(String name);

    Optional<College> findById(Long id);
}
