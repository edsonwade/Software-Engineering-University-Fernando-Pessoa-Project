package ufp.esof.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.persistence.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {
    Optional<Student> findById(Long id);
    Optional<Student> findByStudentName(String name);
}
