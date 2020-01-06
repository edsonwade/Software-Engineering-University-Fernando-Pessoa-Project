package ufp.esof.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Explainer;
import ufp.esof.project.repositories.ExplainerRepo;

import java.util.Optional;

@Service
public class ExplainerService {

    private ExplainerRepo explainerRepo;

    @Autowired
    public ExplainerService(ExplainerRepo explainerRepo) {
        this.explainerRepo = explainerRepo;
    }

    public Optional<Explainer> findByName(String name) {
        return this.explainerRepo.findByName(name);
    }
}
