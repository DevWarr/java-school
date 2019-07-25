package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private List<Course> courseList;

    @Before
    public void setUp()
    {
        courseList = new ArrayList<>();

        Instructor instruct1 = new Instructor("Sally");
        instruct1.setInstructid(1);
        Instructor instruct2 = new Instructor("Lucy");
        instruct2.setInstructid(2);
        Instructor instruct3 = new Instructor("Charlie");
        instruct3.setInstructid(3);

        Student stud1 = new Student("John");
        stud1.setStudid(1);
        Student stud2 = new Student("Julian");
        stud2.setStudid(2);
        Student stud3 = new Student("Mary");
        stud3.setStudid(3);
//        Student stud4 = new Student("Julian");
//        stud4.setStudid(4);
//        Student stud5 = new Student("Tyler");
//        stud5.setStudid(5);
//        Student stud6 = new Student("Kim");
//        stud6.setStudid(6);
//        Student stud7 = new Student("Juan");
//        stud7.setStudid(7);
//        Student stud8 = new Student("Robby");
//        stud8.setStudid(8);
//        Student stud9 = new Student("Roberto");
//        stud9.setStudid(9);
//        Student stud10 = new Student("Bob");
//        stud10.setStudid(10);
//        Student stud11 = new Student("Liz");
//        stud11.setStudid(11);
//        Student stud12 = new Student("June");
//        stud12.setStudid(12);
//        Student stud13 = new Student("Arpil");
//        stud13.setStudid(13);

        Course course1 = new Course("Data Science", instruct1);
        course1.setCourseid(1);
        Course course2 = new Course("Javascript", instruct1);
        course2.setCourseid(2);
        Course course3 = new Course("Node.js", instruct1);
        course3.setCourseid(3);
        Course course4 = new Course("Java Back End", instruct2);
        course4.setCourseid(4);
        Course course5 = new Course("Mobile Android", instruct2);
        course5.setCourseid(5);
        Course course6 = new Course("Mobile iOS", instruct3);
        course6.setCourseid(6);

        course1.getStudents().add(stud1);
        course1.getStudents().add(stud3);
        course2.getStudents().add(stud2);
        course3.getStudents().add(stud3);
        course4.getStudents().add(stud1);
        course6.getStudents().add(stud3);

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        courseList.add(course5);
        courseList.add(course6);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void listAllCourses() throws Exception
    {
        String apiUrl = "courses/all";
        Mockito.when(courseService.findAll()).thenReturn((ArrayList<Course>)courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList.get(2));

        assertEquals("Full list of courses", er, tr);
    }

    @Test
    public void addCourse() throws Exception
    {
        String apiUrl = "courses/new/1";

        Course c7 = new Course("Testing");
        c7.setCourseid(100);
        ObjectMapper mapper = new ObjectMapper();
        String courseString = mapper.writeValueAsString(c7);

        Mockito.when(courseService.save(any(Course.class))).thenReturn(c7);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(courseString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
}
