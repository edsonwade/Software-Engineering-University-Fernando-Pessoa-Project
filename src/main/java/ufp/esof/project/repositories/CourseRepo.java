package ufp.esof.project.repositories;

import org.springframework.data.repository.CrudRepository;
import ufp.esof.project.models.Course;

public interface CourseRepo extends CrudRepository<Course, Long> {
}
