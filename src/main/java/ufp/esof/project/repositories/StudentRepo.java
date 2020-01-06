package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.models.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
}
