package ufp.esof.project.filters;

import lombok.Data;
import ufp.esof.project.persistence.model.Language;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class FilterObject {

    private Language language;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek day;
}
