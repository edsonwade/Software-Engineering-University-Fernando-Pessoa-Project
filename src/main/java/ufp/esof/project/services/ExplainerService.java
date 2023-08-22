package ufp.esof.project.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Explainer;

import java.util.Optional;

@Service
@Repository
public interface ExplainerService {

    Optional<Explainer> getExplainerById(long id);

//   Set<Explainer> getFilteredExplainer(FilterObject filterObject);

    Optional<Explainer> findExplainerByName(String name);

    Iterable<Explainer> findAllExplainers();


    Explainer saveExplainer(Explainer explainer);

    Optional<Explainer> updateExplainer(Explainer currentExplainer, Explainer explainer, Long id);

    boolean deleteById(Long id);
}
