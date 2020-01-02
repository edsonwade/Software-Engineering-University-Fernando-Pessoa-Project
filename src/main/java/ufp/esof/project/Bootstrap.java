package ufp.esof.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ufp.esof.project.models.College;
import ufp.esof.project.repositories.CollegeRepo;

import javax.transaction.Transactional;

@Component
@Transactional
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CollegeRepo collegeRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Bootstrap(CollegeRepo collegeRepo) {
        this.collegeRepo = collegeRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.debug("Startup");

        College college = new College();
        college.setName("Universidade Fernando Pessoa");

        this.collegeRepo.save(college);
    }
}
