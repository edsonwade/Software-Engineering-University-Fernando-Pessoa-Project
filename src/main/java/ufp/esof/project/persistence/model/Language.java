package ufp.esof.project.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "tb_languages")
public class Language implements Serializable {

    private static final long serialVersionUID = 5678698013L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", length = 11)
    private Long languageId;
    @Column(name = "language_name", length = 11)
    private String languageName;

    @JsonIgnore
    @OneToOne
    private Explainer explainer;

    public Language() {
        // default constructor
    }

    public Language(String name) {
        this.languageName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (!Objects.equals(languageId, language.languageId)) return false;
        if (!Objects.equals(languageName, language.languageName))
            return false;
        return Objects.equals(explainer, language.explainer);
    }

    @Override
    public int hashCode() {
        int result = languageId != null ? languageId.hashCode() : 0;
        result = 31 * result + (languageName != null ? languageName.hashCode() : 0);
        result = 31 * result + (explainer != null ? explainer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", name='" + languageName + '\'' +
                ", explainer=" + explainer +
                '}';
    }
}
