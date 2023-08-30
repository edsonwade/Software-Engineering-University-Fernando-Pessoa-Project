package ufp.esof.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.persistence.model.Degree;

import java.util.Optional;

@Repository
public interface DegreeRepo extends CrudRepository<Degree, Long> {
    Optional<Degree> findByDegreeName(String name);
}
