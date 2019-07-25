# Project School Test

A student that completes this project shows that they can:

## Introduction

## Instructions
Start with the school project - you can start with the project provided OR as a stretch goal, use your previous school project!

* [x] In CourseService Add a method for findCourseById
  * [x] Write a unit test for CourseServiceImpl findCourseById

* [x] Write a unit test for CourseServiceImpl for delete by writing two unit tests:
  * [x] Include a unit test called deleteFound
  * [x] Include a unit test called deleteNotFound

* Write a unit test for CourseController for listAllCourses
  * _Typed out, but receiving an error "failed to load ApplicationContext"_

* [x] Write an integrate test for response time for GET /courses/courses 

* [x] In CourseService Add a method for Adding a new course called save
* [x] In CourseController add a controller to POST a new course POST /courses/course/add. Method should be named addNewCourse
  * [x] The course data will contain course name and an instructor object (of an already existing instructor)
    * _Passes in course object and instructor id_
  * [x] No students will be added to the course via this method
  
  * [x] add an unit test for save
  * add an unit test for addNewCourse
    * _also received the error "failed to load ApplicationContext"_
  * add an integration test for POST /courses/course/add

## Stretch Goals
* In StudentService Add a method for Adding a new student called save
* In StudentController add a controller to POST a new student POST /students/student/add. Method should be named addNewStudent
  * This end point only has to add a student. No course information. As a Stretch, Stretch goal, have this end point add students to preexisting courses. 

  * In add an unit test for save
  * In add an unit test for addNewStudent
  * In an integration test for POST /students/student/add
