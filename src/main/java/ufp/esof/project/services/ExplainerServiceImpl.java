package ufp.esof.project.services;

import org.springframework.stereotype.Component;
import ufp.esof.project.filters.FilterObject;
import ufp.esof.project.filters.explainerFilters.ExplainerFilterService;
import ufp.esof.project.models.Course;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.repositories.CourseRepo;
import ufp.esof.project.repositories.ExplainerRepo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class ExplainerServiceImpl implements ExplainerService {

    private ExplainerFilterService explainerFilterService;

    private ExplainerRepo explainerRepo;

    private CourseRepo courseRepo;


    public ExplainerServiceImpl(ExplainerFilterService explainerFilterService, ExplainerRepo explainerRepo, CourseRepo courseRepo) {
        this.explainerRepo = explainerRepo;
        this.courseRepo = courseRepo;
        this.explainerFilterService = explainerFilterService;
    }

    public Optional<Explainer> getById(long id) {
        return explainerRepo.findById(id);
    }

    public Set<Explainer> getFilteredExplainer(FilterObject filterObject) {
        return explainerFilterService.filterExplainer(findAllExplainers(), filterObject);

        // courseFilterService.filterCourses(getAllCourses(), filterObject);
    }

    public Optional<Explainer> findExplainerByName(String name) {

        for (Explainer explainer : findAllExplainers()) {
            if (explainer.getName().equalsIgnoreCase(name)) {
                return Optional.of(explainer);
            }
        }
        return Optional.empty();
    }

    public Set<Explainer> findAllExplainers() {

        Set<Explainer> explainers = new HashSet<>();
        for (Explainer explainer : this.explainerRepo.findAll()) {
            explainers.add(explainer);
        }
        return Collections.unmodifiableSet(explainers);
    }

    public Explainer save(Explainer explainer) {
        return this.explainerRepo.save(explainer);
    }

    public Optional<Explainer> saveExplainer(Explainer explainer, String courseName) {

        Optional<Course> courseOptional = this.courseRepo.findByName(courseName);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();

            course.addExplainer(explainer);
            courseRepo.save(course);
            return explainerRepo.findByName(explainer.getName());
        }
        return Optional.empty();

    }
}