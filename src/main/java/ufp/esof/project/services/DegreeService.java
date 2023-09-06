package ufp.esof.project.services;

import org.springframework.stereotype.Service;
import ufp.esof.project.persistence.model.Degree;

import java.util.Optional;

@Service
public interface DegreeService {
    Iterable<Degree> findAllDegree();
    Optional<Degree>findDegreeById(long id);

    public Optional<Degree> findDegreeByName(String name);

    Degree createDegree(Degree degree);

    Degree updateDegree(long id, Degree degree);

    void deleteDegree(long id);

}
