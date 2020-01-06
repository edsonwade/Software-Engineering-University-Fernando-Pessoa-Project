package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Course;
import ufp.esof.project.models.Degree;
import ufp.esof.project.repositories.CourseRepo;

import java.util.Optional;

@Service
public class CourseService {

    private CourseRepo courseRepo;
    private DegreeService degreeService;

    @Autowired
    public CourseService(CourseRepo courseRepo, DegreeService degreeService) {
        this.courseRepo = courseRepo;
        this.degreeService = degreeService;
    }

    public Iterable<Course> findAllCourses() {
        return this.courseRepo.findAll();
    }

    public Optional<Course> findById(Long id) {
        return this.courseRepo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<Course> optionalCourse = this.findById(id);
        if (optionalCourse.isPresent()) {
            this.courseRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Course> createCourse(Course course) {
        Optional<Course> optionalCourse = this.courseRepo.findByName(course.getName());
        if (optionalCourse.isPresent())
            return Optional.empty();

        Degree degree = course.getDegree();
        Optional<Degree> optionalDegree = this.degreeService.findByName(degree.getName());
        if (optionalDegree.isPresent()) {
            course.setDegree(optionalDegree.get());
            return Optional.of(this.courseRepo.save(course));
        }
        return Optional.empty();
    }
}
