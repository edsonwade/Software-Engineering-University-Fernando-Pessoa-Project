package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.College;
import ufp.esof.project.models.Degree;
import ufp.esof.project.repositories.DegreeRepo;

import java.util.Optional;

@Service
public class DegreeService {

    private DegreeRepo degreeRepo;
    private CollegeService collegeService;

    @Autowired
    public DegreeService(DegreeRepo degreeRepo, CollegeService collegeService) {
        this.degreeRepo = degreeRepo;
        this.collegeService = collegeService;
    }

    public Optional<Degree> findById(Long id) {
        return this.degreeRepo.findById(id);
    }

    public Optional<Degree> findByName(String name) {
        return this.degreeRepo.findByName(name);
    }

    public Iterable<Degree> findAll() {
        return this.degreeRepo.findAll();
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
        // TODO: validate repeated names
        College college = degree.getCollege();
        Optional<College> optionalCollege = collegeService.findByName(college.getName());
        if (optionalCollege.isPresent()) {
            degree.setCollege(optionalCollege.get());
            return Optional.of(this.degreeRepo.save(degree));
        }
        return Optional.empty();
    }
}
