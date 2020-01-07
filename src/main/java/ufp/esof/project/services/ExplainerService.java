package ufp.esof.project.services;

import ufp.esof.project.filters.FilterObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ufp.esof.project.models.Explainer;

import java.util.Optional;
import java.util.Set;

@Service
@Repository
public interface ExplainerService {

    Optional<Explainer> getById(long id);

    Set<Explainer> getFilteredExplainer(FilterObject filterObject);

    Optional<Explainer> findExplainerByName(String name);

    Set<Explainer> findAllExplainers();


    Explainer save(Explainer explainer);

    Optional<Explainer> saveExplainer(Explainer explainer, String courseName);


}

