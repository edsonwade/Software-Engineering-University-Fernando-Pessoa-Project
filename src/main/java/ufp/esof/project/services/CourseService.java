package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Course;
import ufp.esof.project.repositories.CourseRepo;

import java.util.Optional;

@Service
public class CourseService {

    private CourseRepo courseRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Iterable<Course> findAllCourses() {
        return this.courseRepo.findAll();
    }

    public Optional<Course> findById(Long id) {
        return this.courseRepo.findById(id);
    }
}
