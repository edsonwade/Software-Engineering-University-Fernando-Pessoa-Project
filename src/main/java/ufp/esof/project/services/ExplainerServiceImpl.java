package ufp.esof.project.services;

import org.springframework.stereotype.Component;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.exception.ObjectNotFoundByName;
import ufp.esof.project.models.Course;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.repositories.CourseRepo;
import ufp.esof.project.repositories.ExplainerRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ExplainerServiceImpl implements ExplainerService {



    private final ExplainerRepo explainerRepo;

    private final CourseRepo courseRepo;

    public ExplainerServiceImpl(ExplainerRepo explainerRepo, CourseRepo courseRepo) {
        this.explainerRepo = explainerRepo;
        this.courseRepo = courseRepo;
    }


    /** fix this
    public Set<Explainer> getFilteredExplainer(FilterObject filterObject) {
        return explainerFilterService.filterExplainer(findAllExplainers(), filterObject);
     }*/

    public Iterable<Explainer> findAllExplainers() {
        return explainerRepo.findAll();
    }

    public Optional<Explainer> getExplainerById(long id) {
        return Optional.of(explainerRepo.findById(id))
                .orElseThrow(() -> new ObjectNotFoundById("Explainer with " + id + " not found"));
    }

    public Optional<Explainer> findExplainerByName(String name) {
        return Optional.of(explainerRepo.findByName(name))
                .orElseThrow(()->new ObjectNotFoundByName(" Explainer with " + name + " not found"));
    }

    /** fix this
     * public Explainer saveExplainer(Explainer explainer) {
        Explainer newExplainer = new Explainer();
        Optional<Explainer> explainerOptional = this.findExplainerByName(explainer.getName());
        if (explainerOptional.isPresent())
            return Optional.empty();

        explainerOptional = validateExplainerCourses(explainer, explainer);
        if (explainerOptional.isPresent())
            newExplainer = explainerOptional.get();

        return Optional.of(this.explainerRepo.save(newExplainer));
    }*/

    public Explainer saveExplainer(Explainer explainer){
        return null;
    }

    public Optional<Explainer> updateExplainer(Explainer currentExplainer, Explainer explainer, Long id) {
        Explainer newExplainer = new Explainer();
        Optional<Explainer> optionalExplainer = validateExplainerCourses(currentExplainer, explainer);
        if (optionalExplainer.isPresent())
            newExplainer = optionalExplainer.get();

        optionalExplainer = this.explainerRepo.findByName(explainer.getName());
        if (optionalExplainer.isPresent() && (!optionalExplainer.get().getId().equals(id)))
            return Optional.empty();

        newExplainer.setName(explainer.getName());

        return Optional.of(this.explainerRepo.save(newExplainer));
    }

    public boolean deleteById(Long id) {
        Optional<Explainer> optionalExplainer = this.getExplainerById(id);
        if (optionalExplainer.isPresent()) {
            this.explainerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Explainer> validateExplainerCourses(Explainer currentExplainer, Explainer explainer) {
        Set<Course> newCourses = new HashSet<>();
        for (Course course : explainer.getCourses()) {
            Optional<Course> optionalCourse = this.courseRepo.findByName(course.getName());
            if (optionalCourse.isEmpty())
                return Optional.empty();
            Course foundCourse = optionalCourse.get();
            foundCourse.addExplainer(currentExplainer);
            newCourses.add(foundCourse);
        }
        currentExplainer.setCourses(newCourses);
        return Optional.of(currentExplainer);
    }
}