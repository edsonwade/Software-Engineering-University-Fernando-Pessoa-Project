package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.College;
import ufp.esof.project.models.Course;
import ufp.esof.project.models.Degree;
import ufp.esof.project.repositories.CollegeRepo;
import ufp.esof.project.repositories.CourseRepo;
import ufp.esof.project.repositories.DegreeRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DegreeService {

    private DegreeRepo degreeRepo;
    private CollegeRepo collegeRepo;
    private CourseRepo courseRepo;
    private CollegeService collegeService;

    @Autowired
    public DegreeService(DegreeRepo degreeRepo, CollegeService collegeService, CollegeRepo collegeRepo, CourseRepo courseRepo) {
        this.degreeRepo = degreeRepo;
        this.collegeService = collegeService;
        this.collegeRepo = collegeRepo;
        this.courseRepo = courseRepo;
    }

    public Optional<Degree> findById(Long id) {
        return this.degreeRepo.findById(id);
    }

    public Optional<Degree> findByName(String name) {
        return this.degreeRepo.findByName(name);
    }

    public Iterable<Degree> findAllDegrees() {
        return this.degreeRepo.findAll();
    }

    public boolean deleteById(Long id) {
        Optional<Degree> optionalDegree = this.findById(id);
        if (optionalDegree.isPresent()) {
            this.degreeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Degree> createDegree(Degree degree) {
        Optional<Degree> degreeOptional = this.findByName(degree.getName());
        if (degreeOptional.isPresent())
            return Optional.empty();

        Optional<Degree> optionalDegree = validateDegreeCourses(degree, degree);
        if (optionalDegree.isPresent())
            degree = optionalDegree.get();

        College college = degree.getCollege();
        Optional<College> optionalCollege = collegeService.findByName(college.getName());
        if (optionalCollege.isPresent()) {
            degree.setCollege(optionalCollege.get());
            return Optional.of(this.degreeRepo.save(degree));
        }
        return Optional.empty();
    }

    public Optional<Degree> editDegree(Degree currentDegree, Degree degree, Long id) {
        Optional<Degree> optionalDegree = validateDegreeCourses(currentDegree, degree);
        if (optionalDegree.isPresent())
            currentDegree = optionalDegree.get();

        optionalDegree = this.degreeRepo.findByName(degree.getName());
        if (optionalDegree.isPresent()) {
            if (!optionalDegree.get().getId().equals(id))
                return Optional.empty();
        }

        currentDegree.setName(degree.getName());

        Optional<College> optionalCollege = this.collegeRepo.findByName(degree.getCollege().getName());
        if (optionalCollege.isEmpty())
            return Optional.empty();
        currentDegree.setCollege(optionalCollege.get());

        return Optional.of(this.degreeRepo.save(currentDegree));
    }

    public Optional<Degree> validateDegreeCourses(Degree currentDegree, Degree degree) {
        Set<Course> newCourses = new HashSet<>();
        for (Course course : degree.getCourses()) {
            Optional<Course> optionalCourse = this.courseRepo.findByName(course.getName());
            if (optionalCourse.isEmpty())
                return Optional.empty();
            Course foundCourse = optionalCourse.get();
            foundCourse.setDegree(currentDegree);
            newCourses.add(foundCourse);
        }
        currentDegree.replaceCourses(newCourses);
        return Optional.of(currentDegree);
    }
}
