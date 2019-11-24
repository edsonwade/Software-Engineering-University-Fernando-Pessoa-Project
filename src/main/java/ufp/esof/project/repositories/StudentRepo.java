package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import ufp.esof.project.models.Student;

public interface StudentRepo extends CrudRepository<Student, Long> {
}
