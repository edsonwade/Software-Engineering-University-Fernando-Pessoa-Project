package ufp.esof.project.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Student {

    @Id
    private Long Id;
}
