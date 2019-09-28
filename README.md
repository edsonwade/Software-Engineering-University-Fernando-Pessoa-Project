# ESOF

## Objective (High Level)
- Develop a web service that supports colledge tutoring of classes's content.

### Users
- Students
- Tutors

### Functional Requirements
- Tutors must be able to tutor one or more classes in a given course.
- Tutors must be able to provide the schedule of the tutoring per day in the start-end time format.
- Tutors must be able to provide the available idioms for the tutoring.
- Students must be able to search for tutors.
	-- Minimum acceptance criteria:
		--- Class
		--- Day
		--- Time period (start-end time format)
		--- Idioms
		--- It must be possible to do combinations with the previous filters.
- Students must be able to book a 1h tutoring for a given tutor.
- Tutors must not be able to allow simultaneous bookings.
- The implementation must respect the University model, taking into consideration it's schools, it's courses and respective classes.


## To Clarify
- Is there a need for a database and an ER-Model? (most likely)
