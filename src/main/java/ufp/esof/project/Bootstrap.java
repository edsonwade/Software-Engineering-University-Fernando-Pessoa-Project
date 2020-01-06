package ufp.esof.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ufp.esof.project.models.College;
import ufp.esof.project.models.Course;
import ufp.esof.project.models.Degree;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.repositories.CollegeRepo;
import ufp.esof.project.repositories.CourseRepo;
import ufp.esof.project.repositories.DegreeRepo;
import ufp.esof.project.repositories.ExplainerRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private DegreeRepo degreeRepo;
    private CollegeRepo collegeRepo;
    private CourseRepo courseRepo;
    private ExplainerRepo explainerRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Bootstrap(DegreeRepo degreeRepo, CollegeRepo collegeRepo, CourseRepo courseRepo, ExplainerRepo explainerRepo) {
        this.degreeRepo = degreeRepo;
        this.collegeRepo = collegeRepo;
        this.courseRepo = courseRepo;
        this.explainerRepo = explainerRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("Startup");

        Degree degree = new Degree("Degree1");

        this.degreeRepo.save(degree);

        ArrayList<College> colleges = new ArrayList<>();
        colleges.add(new College("Universidade Fernando Pessoa"));
        colleges.add(new College("Universidade Católica do Porto"));
        colleges.add(new College("Faculdade de Engenharia da Universidade do Porto"));

        this.collegeRepo.saveAll(colleges);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Engenharia de Software"));
        courses.add(new Course("Bases de dados"));

        this.courseRepo.saveAll(courses);

        ArrayList<Explainer> explainers = new ArrayList<>();
        explainers.add(new Explainer("Prof. João"));
        explainers.add(new Explainer("Prof. Pedro"));

        this.explainerRepo.saveAll(explainers);

    }
}
