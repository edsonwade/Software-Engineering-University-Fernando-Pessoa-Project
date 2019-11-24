package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import ufp.esof.project.models.College;

public interface CollegeRepo extends CrudRepository<College, Long> {
}
