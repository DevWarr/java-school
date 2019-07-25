package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest
{
    @Autowired
    CourseService courseService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void AfindCourseById()
    {
        assertEquals(2, courseService.findCourseById(2).getCourseid());
    }

    @Test
    public void BdeleteFound()
    {
        courseService.delete(2);
        assertEquals(5, courseService.findAll().size());
    }

    @Test (expected = ResourceNotFoundException.class)
    public void CdeleteNotFound()
    {
        courseService.delete(75);
        assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void DsaveCourse()
    {
        Course course7 = new Course("Testing");
        Course newCourse = courseService.save(course7);

        assertNotNull(newCourse);

        Course foundCourse = courseService.findCourseById(newCourse.getCourseid());
        assertEquals(newCourse.getCoursename(), foundCourse.getCoursename());

    }
}
