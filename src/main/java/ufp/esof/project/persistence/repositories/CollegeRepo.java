package ufp.esof.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.persistence.model.College;

import java.util.Optional;

@Repository
public interface CollegeRepo extends CrudRepository<College, Long> {
    Optional<College> findByName(String name);

    Optional<College> findById(Long id);
}
