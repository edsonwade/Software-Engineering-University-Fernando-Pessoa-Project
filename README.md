# ESOF
Code Review from the Subject Engineering Software project, realized in University Fernando Pessoa in 2019.

### Update Project date
-   06-09-2023

### Table of Contents
- [about](#about)
- [Implementations](#implementations)
- [Contributing](#contributing)

## About
Implementing the best practical of Clean Code, Design Pattern ,Solid Principal and Unit Testing .

## Objective (High Level)
- Develop a web service that supports college tutoring of class's content.

### Users
- Students
- Tutors

### Functional Requirements
- Tutors must be able to tutor one or more classes in a given course.
- Tutors must be able to provide the schedule of the tutoring per day in the start-end time format.
- Tutors must be able to provide the available idioms for the tutoring.
- Students must be able to search for tutors.
Minimum acceptance criteria:
	Class
	Day
	Time period (start-end time format)
	Idioms
	It must be possible to do combinations with the previous filters.
- Students must be able to book a 1h tutoring for a given tutor.
- Tutors must not be able to allow simultaneous bookings.
- The implementation must respect the University model, taking into consideration it's schools, it's courses and respective classes.

### Implementation Fases
- Fase 1:
Develop a web service (WS1) that allows to do the system managing:
- Create Tutors
- Update Tutors
- List Tutors
- Create Schools
- Update Schools
- List Schools
- Create Courses
- Update Courses
- List Courses
- Create Classes
- Update Classes
- List Classes
- Book Tutoring
- WS1 should be an instance of a University (Each University has it's own WS)

Minimum Required Endpoints:
- POST /explicador
- POST /faculdade
- POST /curso/{faculdade}
- POST /cadeira/{curso}
- PUT /explicador/{curso}
- PUT /explicador (deverá ser utilizado para definir as disponibilidades do explicador)
- GET /explicador
- GET /explicador/{nome_explicador}
- GET /explicador?curso={curso}&dia={dia}&inicio={hora_inicio}&fim={hora_fim}
- POST /atendimento

- Fase 2
Develop a web service (WS2) to do searches in the different instances of WS1 (minimum 2):
The services offered by WS2 must use the services exposed by the different instances of WS1.

Minimum Required Endpoints:
- POST /explicador/{universidade}
- PUT /explicador (must be used to define tutor schedule)
- PUT /explicador/{universidade}/{curso}
- GET /explicador?curso={curso}&dia={dia}&inicio={hora_inicio}&fim={hora_fim}
- GET /explicador/{universidade}?curso={curso}&dia={dia}&inicio={hora_inicio}&fim={hora_fim}
- POST /atendimento/{universidade}

## Implementations
1. Maven v3.6.2
2. JPA(Java Persistence API),Hibernate
3. Flyway Migration (Mysql v8, H2, and MariaDB)
4. Swagger (OpenAPI), Postman
5. Docker Composer
6. Integration Testing (JUnit5 and Mockito),Functional Test(Cucumber)
7. Testcontainers(Docker Container)
8. Pipeline CI/CD with Github Actions
9. Grafana, Prometheus

# Contributing
- [Vanilson Muhongo](https://www.github.com/edsonwade)
- [João Rodrigo Almeida Castro](https://github.com/31186)
- [Fel Galvão](https://github.com/FelGalvao)


# Code Reviews 
-[Codacy-white](https://app.codacy.com/gh/edsonwade/Software-Engineering-University-Fernando-Pessoa-Project/dashboard)