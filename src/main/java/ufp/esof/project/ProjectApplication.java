package ufp.esof.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;

@SpringBootApplication
@EntityScan("ufp.esof.project.persistence.model")


public class ProjectApplication {

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        SpringApplication.run(ProjectApplication.class, args);
    }

}
