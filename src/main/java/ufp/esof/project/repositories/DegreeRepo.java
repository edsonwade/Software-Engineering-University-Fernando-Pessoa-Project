package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.models.Degree;

@Repository
public interface DegreeRepo extends CrudRepository<Degree, Long> {
}
