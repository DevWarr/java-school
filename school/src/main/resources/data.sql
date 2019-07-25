DELETE
FROM studcourses;

DELETE
FROM course;

DELETE
FROM student;

DELETE
FROM instructor;

INSERT INTO instructor (instructid, instructname)
    VALUES(1, 'Sally'),
          (2, 'Lucy'),
          (3, 'Charlie');

INSERT INTO course (courseid, coursename, instructid)
	VALUES (1, 'Data Science', 1),
           (2, 'JavaScript', 1),
           (3, 'Node.js',  1),
           (4, 'Java Back End', 2),
           (5, 'Mobile Android', 2),
           (6, 'Mobile iOS',  3);

INSERT INTO student (studid, studname)
    VALUES (1, 'John'),
           (2, 'Julian'),
           (3, 'Mary'),
           (4, 'Julian'),
           (5, 'Tyler'),
           (6, 'Kim'),
           (7, 'Juan'),
           (8, 'Robby'),
           (9, 'Roberto'),
           (10, 'Bob'),
           (11, 'Liz'),
           (12, 'June'),
           (13, 'April');

INSERT INTO studcourses (studid, courseid)
    VALUES (1, 1),
           (1, 4),
           (2, 2),
           (3, 3),
           (3, 1),
           (3, 6);

INSERT INTO users (userid, username, password)
    VALUES (1, 'barnbarn', '1LuvM4th'),
           (2, 'admin', 'password'),
           (3, 'Bob', 'password'),
           (4, 'Jane', 'password');

INSERT INTO roles (roleid, rolename)
    VALUES (1, 'admin'),
           (2, 'user');

INSERT INTO userroles (userid, roleid)
    VALUES (1, 2),
           (2, 1),
           (2, 2),
           (3, 2),
           (4, 2);

alter sequence hibernate_sequence restart with 20;