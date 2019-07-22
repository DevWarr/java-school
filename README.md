# Project School Test

A student that completes this project shows that they can:

## Introduction

## Instructions

Starting with the java-school application

[x] Get the endpoint GET /courses/studcount working

Add appropriate exception handling routines. Required exceptions to handle are when
  * [x] a resource is not found
  * [x] the wrong data type is used for a path variable
  * [x] a non-handled endpoint is accessed (a URL not found exception)

Add appropriate logging routines. Required logging include
  * [x] Activating actuator endpoints
  * [x] Tomcat logging routed to a separate log file
  * [x] Custom logging under each Get endpoint saying the endpoint has been accessed
    * [x] should only go to console
    * [x] for example when a client calls PUT /students/Student log should say "PUT /students/Student accessed"
    * [x] include in log any appropriate parameters sent to the end point
    * [x] for each log, add a date and time stamp.
  * [x] Note: put the log files under the directory /tmp/var/logs/lambdajx You may have to create some directories for this to work.


## Stretch Goal
* [x] Add endpoints to create, update, delete an instructor.
* [x] Add endpoints to add, delete a student from a course.
  * _Already existed in the initial project code._
* Add User Authentication using OAuth2 to the Java-School Application
* After adding User Authentication, Add Endpoints to create, read, update, delete a user.
