package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface CourseService
{
    ArrayList<Course> findAll();

    ArrayList<Course> findAllPagination(Pageable pageable);

    Course findCourseById(long id);

    ArrayList<CountStudentsInCourses> getCountStudentsInCourse();

    void delete(long id);

    Course save(Course newCourse, long instructorId);
}
