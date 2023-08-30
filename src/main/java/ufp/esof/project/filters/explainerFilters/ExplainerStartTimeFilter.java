package ufp.esof.project.filters.explainerFilters;


import ufp.esof.project.persistence.model.Explainer;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ExplainerStartTimeFilter implements ExplainerFilter {


    private final LocalTime startTime;

    public ExplainerStartTimeFilter(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override

    public Set<Explainer> filter(Set<Explainer> explainers) {
        if (startTime == null) return explainers;
        return explainers.stream().filter(explainer -> explainer.getAppointments()
                .stream().anyMatch(appointment -> appointment.getStartTime().getHour() <= startTime.getHour()))
                .collect(Collectors.toSet());

    }
}

