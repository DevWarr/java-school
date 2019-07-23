package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.view.CountStudentsInCourses;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/courses")
public class CourseController
{
    // Adding in the Logger (MAKE SURE dependency is added to pom.xml!)
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;



    @ApiOperation(value = "Returns all Courses listed on one page", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All courses Found", responseContainer = "List"),
            @ApiResponse(code = 404, message = "Courses not found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCourses(HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        ArrayList<Course> myCourses = courseService.findAll();
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }



    @ApiOperation(value = "Returns all Courses with Paging Ability", responseContainer = "List")
    @ApiImplicitParams({ // These are all of the param definitions a user would need. Fancy~!
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All courses Found", responseContainer = "List"),
            @ApiResponse(code = 404, message = "Courses not found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/paged", produces = {"application/json"})
    public ResponseEntity<?> listAllCoursesPagination(@PageableDefault(size = 3) Pageable pageable, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        ArrayList<Course> myCourses = courseService.findAllPagination(pageable);
        return new ResponseEntity<>(myCourses, HttpStatus.OK);
    }



    @ApiOperation(value = "Returns each course with the number of students enrolled", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All courses Found", responseContainer = "List"),
            @ApiResponse(code = 404, message = "Courses not found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/studcount", produces = {"application/json"})
    public ResponseEntity<?> getCountStudentsInCourses(HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        return new ResponseEntity<>(courseService.getCountStudentsInCourse(), HttpStatus.OK);
    }



    @ApiOperation(value = "Creates a new Course with the data given in the request body", notes = "The number in the URL indicates the id of the instructor of the new course. It is not required to add any instructors or students in the JSON when creating a new course.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New Course Created", response = void.class),
            @ApiResponse(code = 500, message = "Error adding course to database", response = ErrorDetail.class)
    })
    @PostMapping(value = "/new/{instr}", consumes = {"application/json"})
    public ResponseEntity<?> addNewCourse(//@ApiParam(value = "New Course Object", required = true)
                                              @RequestBody Course course,
                                          @ApiParam(value = "Id of the instructor who is assigned to the course.", required = true, example = "1")
                                              @PathVariable long instr,
                                          HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        course = courseService.save(course, instr);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @ApiOperation(value = "Deletes the course with the given id", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Course deleted", response = void.class),
            @ApiResponse(code = 500, message = "Error deleting course from Database", response = ErrorDetail.class)
    })
    @DeleteMapping("/courses/{courseid}")
    public ResponseEntity<?> deleteCourseById(@ApiParam(value = "Course Id", required = true, example = "3")
                                                  @PathVariable long courseid,
                                              HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        courseService.delete(courseid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
