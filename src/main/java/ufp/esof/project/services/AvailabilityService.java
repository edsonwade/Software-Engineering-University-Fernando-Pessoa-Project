package ufp.esof.project.services;

import org.springframework.stereotype.Service;
import ufp.esof.project.persistence.model.Availability;
import ufp.esof.project.persistence.model.Explainer;
import ufp.esof.project.persistence.repositories.AvailabilityRepo;
import ufp.esof.project.persistence.repositories.ExplainerRepo;

import java.util.Optional;

@Service
public class AvailabilityService {

    private final AvailabilityRepo availabilityRepo;

    private final ExplainerRepo explainerRepo;


    public AvailabilityService(AvailabilityRepo availabilityRepo, ExplainerRepo explainerRepo) {
        this.availabilityRepo = availabilityRepo;
        this.explainerRepo = explainerRepo;
    }

    public Iterable<Availability> findAll() {
        return this.availabilityRepo.findAll();
    }

    public Optional<Availability> findById(Long id) {
        return this.availabilityRepo.findById(id);
    }

    public boolean deleteById(Long id) {
        Optional<Availability> optionalAvailability = this.findById(id);
        if (optionalAvailability.isPresent()) {
            this.availabilityRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Availability> createAvailability(Availability availability) {
        Availability newAvailability = new Availability();

        Optional<Explainer> optionalExplainer = this.explainerRepo
                .findByExplainerName(availability
                        .getExplainer()
                        .getExplainerName());
        if (optionalExplainer.isEmpty())
            return Optional.empty();

        newAvailability.setExplainer(optionalExplainer.get());

        //TODO: add validations
        newAvailability.setDayOfWeek(availability.getDayOfWeek());
        newAvailability.setStart(availability.getStart());
        newAvailability.setEnd(availability.getEnd());
        newAvailability.setDayOfWeek(availability.getDayOfWeek());

        return Optional.of(this.availabilityRepo.save(newAvailability));
    }
@SuppressWarnings("unused")
    public Optional<Availability> editAvailability(Availability currentAvailability, Availability availability, Long id) {
        Availability newAvailability;

        Optional<Availability> optionalAvailability = this.availabilityRepo.findById(id);
        if (optionalAvailability.isEmpty())
            return Optional.empty();

        newAvailability = optionalAvailability.get();

        Optional<Explainer> optionalExplainer = this.explainerRepo
                .findByExplainerName(availability
                        .getExplainer()
                        .getExplainerName());
        if (optionalExplainer.isEmpty())
            return Optional.empty();

        newAvailability.setExplainer(optionalExplainer.get());

        //TODO: add validations
        newAvailability.setDayOfWeek(availability.getDayOfWeek());
        newAvailability.setStart(availability.getStart());
        newAvailability.setEnd(availability.getEnd());
        newAvailability.setDayOfWeek(availability.getDayOfWeek());

        return Optional.of(this.availabilityRepo.save(newAvailability));
    }

}
