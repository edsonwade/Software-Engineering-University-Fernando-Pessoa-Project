package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Student;
import ufp.esof.project.repositories.StudentRepo;

import java.util.Optional;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Optional<Student> findByName(String name) {
        return this.studentRepo.findByName(name);
    }
}
