insert into tb_students(student_id,student_name, email) values (1,'Benny Eldredge', 'beldredge0@craigslist.org');
insert into tb_students( student_id,student_name, email) values (2,'Lacy Banke', 'lbanke1@com.com');
insert into tb_students(student_id,student_name, email) values (3,'Karie Treadway', 'ktreadway2@ft.com');
insert into tb_students(student_id,student_name, email) values (4,'Brander Trusler', 'btrusler3@amazon.co.uk');
insert into tb_students(student_id,student_name, email) values (5,'Olia Elson', 'oelson4@sun.com');
insert into tb_students(student_id,student_name, email) values (6,'Dino Dytham', 'ddytham5@nih.gov');
insert into tb_students(student_id,student_name, email) values (7,'Lucian Boldock', 'lboldock6@parallels.com');
insert into tb_students(student_id,student_name, email) values (8,'Parke Crossman', 'pcrossman7@domainmarket.com');
insert into tb_students(student_id,student_name, email) values (9,'Tracy Huyton', 'thuyton8@google.com');
insert into tb_students(student_id,student_name, email) values (10,'Desiri Meier', 'dmeier9@fc2.com');



insert into tb_explainers (explainer_id , explainer_name, email) values (1, 'Rocky Saunders', 'rsaunders0@usnews.com');
insert into tb_explainers (explainer_id , explainer_name, email) values (2, 'Gaynor Ramberg', 'gramberg1@example.com');
insert into tb_explainers (explainer_id , explainer_name, email) values (3, 'Stanley McGuffie', 'smcguffie2@yale.edu');
insert into tb_explainers (explainer_id , explainer_name, email) values (4, 'Kiah Sore', 'ksore3@google.co.jp');
insert into tb_explainers (explainer_id , explainer_name, email) values (5, 'Jeralee Wigg', 'jwigg4@unblog.fr');
insert into tb_explainers (explainer_id , explainer_name, email) values (6, 'Julia Finnick', 'jfinnick5@posterous.com');
insert into tb_explainers (explainer_id , explainer_name, email) values (7, 'Gerhardine Fend', 'gfend6@vkontakte.ru');
insert into tb_explainers (explainer_id , explainer_name, email) values (8, 'Anastasia Trigwell', 'atrigwell7@51.la');
insert into tb_explainers (explainer_id , explainer_name, email) values (9, 'Margalo Pixton', 'mpixton8@about.com');
insert into tb_explainers (explainer_id , explainer_name, email) values (10, 'Joyous Halsted', 'jhalsted9@msn.com');
insert into tb_explainers (explainer_id , explainer_name, email) values (11, 'Sauveur Bollard', 'sbollarda@mashable.com');
insert into tb_explainers (explainer_id , explainer_name, email) values (12, 'Horacio Fielding', 'hfieldingb@toplist.cz');
insert into tb_explainers (explainer_id , explainer_name, email) values (13, 'Luke Gosland', 'lgoslandc@weather.com');
insert into tb_explainers (explainer_id , explainer_name, email) values (14, 'Sumner Wordley', 'swordleyd@github.io');
insert into tb_explainers (explainer_id , explainer_name, email) values (15, 'Marvin Morrad', 'mmorrade@lulu.com');

insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (1, 1, 1, '1900-01-01 00:00', '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (2, 1, 1, '1900-01-01 00:00',  '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (3, 3, 6, '1900-01-01 00:00',  '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (4, 2, 15, '1900-01-01 00:00',  '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (5, 3, 13, '1900-01-01 00:00',  '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (6, 9, 10, '1900-01-01 00:00',  '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (7, 6, 2, '1900-01-01 00:00',  '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (8, 5, 9,  '1900-01-01 00:00',  '1900-01-01 00:00');
insert into tb_appointments (appointment_id,  student_student_id, explainer_explainer_id, start_time, end_time) values (9, 7, 4, '1900-01-01 00:00',  '1900-01-01 00:00');


insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (1, 1, '12:29', '3:37', 2);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (2, 2, '2:29 ', '1:55', 10);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (3, 7, '8:39 ', '12:24 ', 1);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (4, 2, '7:59 ', '4:36 ', 15);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (5, 5, '6:13', '11:22',13);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (6, 2, '7:55 ', '5:33', 2);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (7, 5, '2:54', '4:23 ', 9);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (8, 7, '4:45', '7:40 ', 4);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (9, 5, '2:35', '7:42', 6);
insert into tb_availabilitys (availability_id, day_of_week, availability_start, availability_end, explainer_explainer_id) values (10, 2, '3:23', '11:12 ',1);

insert into tb_colleges (college_id, name) values (308493, 'Universidade de Aveiro');
insert into tb_colleges (college_id, name) values (44335, 'Universidade de Fernando Pessoa');
insert into tb_colleges (college_id, name) values (116107, 'Universidade do Porto');
insert into tb_colleges (college_id, name) values (558014, 'Universidade de Évora');
insert into tb_colleges (college_id, name) values (39861, 'Universidade de Coimbra');
insert into tb_colleges (college_id, name) values (193455, 'Universidade de Lisboa');
insert into tb_colleges (college_id, name) values (628200, 'Universidade de Aveiro');
insert into tb_colleges (college_id, name) values (201113, 'Universidade de Fundão');
insert into tb_colleges (college_id, name) values (451959, 'Universidade de Coimbra');
insert into tb_colleges (college_id, name) values (333964, 'Universidade de Braga');

insert into tb_languages (language_id, language_name, explainer_explainer_id) values (1, 'germany', 1);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (2, 'french', 2);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (3, 'italian', 3);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (4, 'french', 4);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (5, 'spanish', 5);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (6, 'germany', 6);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (7, 'portuguese', 7);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (8, 'portuguese', 8);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (9, 'portuguese', 9);
insert into tb_languages (language_id, language_name, explainer_explainer_id) values (10, 'spanish', 10);