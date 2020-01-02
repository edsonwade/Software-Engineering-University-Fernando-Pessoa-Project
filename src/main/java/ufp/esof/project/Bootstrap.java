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
import ufp.esof.project.repositories.CollegeRepo;
import ufp.esof.project.repositories.DegreeRepo;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private DegreeRepo degreeRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Bootstrap(DegreeRepo degreeRepo) {
        this.degreeRepo = degreeRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("Startup");

        Degree degree = new Degree("Degree1");

        this.degreeRepo.save(degree);
    }
}
