# 📘 ESOF Project

## 📅 Update Project Date
- 06-09-2023

## 📋 Table of Contents
- [About](#about)
- [Implementations](#implementations)
- [Contributing](#contributing)
- [License](#license)

## 📝 About
This project involves the code review of the Software Engineering project conducted at University Fernando Pessoa in 2019. The project focused on implementing best practices of Clean Code, Design Patterns, SOLID Principles, and Unit Testing.

## 🎯 Objective (High Level)
Develop a web service that supports college tutoring of class content.

### 🧑‍🎓 Users
- Students
- Tutors

### 📜 Functional Requirements
- Tutors must be able to tutor one or more classes in a given course.
- Tutors must be able to provide the schedule of tutoring per day in the start-end time format.
- Tutors must be able to provide the available idioms for tutoring.
- Students must be able to search for tutors based on various criteria.
- Students must be able to book a 1-hour tutoring session with a given tutor.
- Tutors must not be able to allow simultaneous bookings.
- The implementation must respect the University model, including its schools, courses, and classes.

### 🚀 Implementation Phases
- Phase 1:
  - Develop a web service (WS1) for system management.
  - Minimum Required Endpoints:
    - POST /explicador
    - POST /faculdade
    - POST /curso/{faculdade}
    - POST /cadeira/{curso}
    - PUT /explicador/{curso}
    - PUT /explicador
    - GET /explicador
    - GET /explicador/{nome_explicador}
    - GET /explicador?curso={curso}&dia={dia}&inicio={hora_inicio}&fim={hora_fim}
    - POST /atendimento
- Phase 2:
  - Develop a web service (WS2) for searching in different instances of WS1.
  - Minimum Required Endpoints:
    - POST /explicador/{universidade}
    - PUT /explicador
    - PUT /explicador/{universidade}/{curso}
    - GET /explicador?curso={curso}&dia={dia}&inicio={hora_inicio}&fim={hora_fim}
    - GET /explicador/{universidade}?curso={curso}&dia={dia}&inicio={hora_inicio}&fim={hora_fim}
    - POST /atendimento/{universidade}

## 🛠️ Implementations
1. Maven v3.6.2
2. JPA (Java Persistence API), Hibernate
3. Flyway Migration (MySQL v8, H2, and MariaDB)
4. Swagger (OpenAPI), Postman
5. Docker Composer
6. Integration Testing (JUnit5 and Mockito), Functional Test (Cucumber)
7. Testcontainers (Docker Container)
8. Pipeline CI/CD with Github Actions
9. Grafana, Prometheus
10. Spring Security

## 🔍 Code Reviews
- [Codacy-White](https://app.codacy.com/gh/edsonwade/Software-Engineering-University-Fernando-Pessoa-Project/dashboard)

## 🤝 Contributing
- [Vanilson Muhongo](https://www.github.com/edsonwade)
- [João Rodrigo Almeida Castro](https://github.com/31186)
- [Fel Galvão](https://github.com/FelGalvao)

## 📝 License
This project is licensed under the [MIT License](LICENSE).
