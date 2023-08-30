package ufp.esof.project.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ufp.esof.project.persistence.model.Explainer;

import java.util.Optional;

@Repository
public interface ExplainerRepo extends CrudRepository<Explainer, Long> {
    Optional<Explainer> findByExplainerName(String name);
}
