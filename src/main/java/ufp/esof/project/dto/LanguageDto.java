package ufp.esof.project.dto;

import lombok.Value;
import ufp.esof.project.persistence.model.Language;

import java.io.Serializable;

/**
 * DTO for {@link Language}
 */
@Value
public class LanguageDto implements Serializable {
    private static final long serialVersionUID = 3549270929368993549L;
    Long languageId;
    String languageName;
}