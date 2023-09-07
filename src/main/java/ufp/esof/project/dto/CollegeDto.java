package ufp.esof.project.dto;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ufp.esof.project.persistence.model.College}
 */
@Value
public class CollegeDto implements Serializable {
    private static final long serialVersionUID = 8335249187356306657L;
    Long collegeId;
    String name;
    Set<DegreeDto> degrees;
}