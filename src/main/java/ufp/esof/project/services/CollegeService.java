package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.College;
import ufp.esof.project.repositories.CollegeRepo;

import java.util.Optional;

@Service
public class CollegeService {

    private CollegeRepo collegeRepo;

    @Autowired
    public CollegeService(CollegeRepo collegeRepo) {
        this.collegeRepo = collegeRepo;
    }

    public Optional<College> findByName(String name) {
        return this.collegeRepo.findByName(name);
    }
}
