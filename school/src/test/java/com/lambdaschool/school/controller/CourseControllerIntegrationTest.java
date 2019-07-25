package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initializeRestAssuredMockMvcWebAppContext()
    {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    // GET courses/all
    @Test
    public void whenMeasuredResponseTime()
    {
        given().when().get("courses/all").then().time(lessThan(5000L));
    }

    // POST courses/new
    @Test
    public void givenPostARestaurant() throws Exception
    {
        String apiUrl = "/courses/new";

        Course c7 = new Course("Testing");
        c7.setCourseid(100);
        ObjectMapper mapper = new ObjectMapper();
        String courseString = mapper.writeValueAsString(c7);

        given().contentType("application/json").body(courseString).when().post(apiUrl).then().statusCode(201);
    }
}
