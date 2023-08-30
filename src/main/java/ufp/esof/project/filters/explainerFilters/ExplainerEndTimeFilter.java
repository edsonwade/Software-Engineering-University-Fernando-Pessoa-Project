package ufp.esof.project.filters.explainerFilters;


import ufp.esof.project.persistence.model.Explainer;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ExplainerEndTimeFilter implements ExplainerFilter {


    private final LocalTime endTime;

    public ExplainerEndTimeFilter(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override

    public Set<Explainer> filter(Set<Explainer> explainers) {
        if (endTime == null) return explainers;
        return explainers.stream().filter(explainer -> explainer.getAppointments()
                .stream().anyMatch(appointment -> appointment.getExpectedEndTime().getHour() <= endTime.getHour()))
                .collect(Collectors.toSet());

    }
}
