package ufp.esof.project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.exception.ObjectNotFoundByName;
import ufp.esof.project.persistence.model.Degree;
import ufp.esof.project.persistence.repositories.CourseRepo;
import ufp.esof.project.persistence.repositories.DegreeRepo;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Component
public class DegreeServiceImpl implements DegreeService {

    private static final Logger logger = LoggerFactory.getLogger(DegreeServiceImpl.class);
    public static final String DOES_NOT_EXISTS = " does not exists";

    private final DegreeRepo degreeRepo;
    private final CourseRepo courseRepo;
    private final CollegeService collegeService;


    public DegreeServiceImpl(DegreeRepo degreeRepo, CollegeService collegeService, CourseRepo courseRepo) {
        this.degreeRepo = degreeRepo;
        this.collegeService = collegeService;
        this.courseRepo = courseRepo;
    }


    @Override
    public Iterable<Degree> findAllDegree() {
        logger.info("list all degrees");
        return degreeRepo.findAll();
    }

    @Override
    public Optional<Degree> findDegreeById(long degreeId) {
        Optional<Degree> degree = degreeRepo.findById(degreeId);
        if (degree.isEmpty()) {
            logger.error("The degree with id{} {}", degreeId, DOES_NOT_EXISTS);
            throw new ObjectNotFoundById("degree with id " + degreeId + DOES_NOT_EXISTS);
        }
        return degree;
    }

    @Override
    public Optional<Degree> findDegreeByName(String degreeName) {
        Optional<Degree> degree = degreeRepo.findByDegreeName(degreeName);
        if (degree.isEmpty()) {
            throw new ObjectNotFoundByName("degree with name " + degreeName + DOES_NOT_EXISTS);
        }
        return degree;
    }

    @Override
    public Degree createDegree(@NotNull Degree degree) {
        return degreeRepo.save(degree);
    }

    @Override
    public Degree updateDegree(long degreeId, @NotNull Degree degree) {
        Optional<Degree> degrees = degreeRepo.findById(degreeId);
        if (degrees.isEmpty()) {
            throw new ObjectNotFoundByName("degree with name " + degreeId + DOES_NOT_EXISTS);
        }
        Degree newDegree = new Degree();
        newDegree.setDegreeId(degree.getDegreeId());
        newDegree.setDegreeName(degree.getDegreeName());
        newDegree.setCourses(newDegree.getCourses());
        newDegree.setCollege(newDegree.getCollege());

        return degreeRepo.save(newDegree);

    }

    @Override
    public void deleteDegree(long degreeId) {
        if (!degreeRepo.existsById(degreeId)) {
            throw new ObjectNotFoundById(
                    "Degree with id " + degreeId + " does not exists");
        }
        degreeRepo.deleteById(degreeId);

    }

}
