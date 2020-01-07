package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Course;
import ufp.esof.project.models.Degree;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.repositories.CourseRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {

    private CourseRepo courseRepo;
    private DegreeService degreeService;
    private ExplainerService explainerService;

    @Autowired
    public CourseService(CourseRepo courseRepo, DegreeService degreeService, ExplainerService explainerService) {
        this.courseRepo = courseRepo;
        this.degreeService = degreeService;
        this.explainerService = explainerService;
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
        //TODO: explainers and students
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

    public Optional<Course> editCourse(Course currentCourse, Course course, Long id) {
        //TODO: students
        Set<Explainer> newExplainers = new HashSet<>();
        for (Explainer explainer : course.getExplainers()) {
            Optional<Explainer> optionalExplainer = this.explainerService.findExplainerByName(explainer.getName());
            if (optionalExplainer.isEmpty())
                return Optional.empty();
            Explainer foundExplainer = optionalExplainer.get();
            foundExplainer.addCourse(currentCourse);
            newExplainers.add(foundExplainer);
        }

        currentCourse.setExplainers(newExplainers);

        Optional<Course> optionalCourse = this.courseRepo.findByName(course.getName());
        if (optionalCourse.isPresent())
            if (!optionalCourse.get().getId().equals(id))
                return Optional.empty();

        currentCourse.setName(course.getName());

        Optional<Degree> optionalDegree = this.degreeService.findByName(course.getDegree().getName());
        if (optionalDegree.isEmpty())
            return Optional.empty();

        currentCourse.setDegree(optionalDegree.get());

        return Optional.of(this.courseRepo.save(currentCourse));
    }
}
