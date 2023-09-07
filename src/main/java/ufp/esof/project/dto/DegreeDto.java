package ufp.esof.project.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ufp.esof.project.persistence.model.Degree}
 */
@Value
public class DegreeDto implements Serializable {
    private static final long serialVersionUID = 2957545751298584519L;
    Long degreeId;
    String degreeName;
}