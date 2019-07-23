package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.StudentService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController
{
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    // Please note there is no way to add students to course yet!



    @ApiOperation(value = "Returns all Students listed on one page.", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All students Found", responseContainer = "List"),
            @ApiResponse(code = 404, message = "Students not found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> listAllStudents(HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        List<Student> myStudents = studentService.findAll();
        return new ResponseEntity<>(myStudents, HttpStatus.OK);
    }



    @ApiOperation(value = "Returns all Students with Paging Ability.", responseContainer = "List")
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
            @ApiResponse(code = 200, message = "All students Found", responseContainer = "List"),
            @ApiResponse(code = 404, message = "Students not found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/paged", produces = {"application/json"})
    public ResponseEntity<?> listAllStudentsPagination(@PageableDefault(size = 3) Pageable pageable, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        List<Student> myStudents = studentService.findAllPagination(pageable);
        return new ResponseEntity<>(myStudents, HttpStatus.OK);
    }



    @ApiOperation(value = "Returns a specific student whose id matches the integer in the URL.", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student found", response = Student.class),
            @ApiResponse(code = 404, message = "No student found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/Student/{StudentId}",
                produces = {"application/json"})
    public ResponseEntity<?> getStudentById(@ApiParam(value = "Student Id", required = true, example = "2")
                                                @PathVariable Long StudentId,
                                            HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        Student r = studentService.findStudentById(StudentId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }



    @ApiOperation(value = "Returns a specific student whose name matches(or includes) the string in the URL.", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student found", response = Student.class),
            @ApiResponse(code = 404, message = "No student found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/student/namelike/{name}",
                produces = {"application/json"})
    public ResponseEntity<?> getStudentByNameContaining(@ApiParam(value = "Student name", required = true, example = "Sam")
                                                            @PathVariable String name,
                                                        HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        List<Student> myStudents = studentService.findStudentByNameLike(name);
        return new ResponseEntity<>(myStudents, HttpStatus.OK);
    }



    @ApiOperation(value = "Adds a new student to the database using the given JSON data.", notes = "location header links to the student's id URL", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Student created", response = void.class),
            @ApiResponse(code = 500, message = "Unable to add student into database", response = ErrorDetail.class)
    })
    @PostMapping(value = "/Student",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewStudent(@Valid
                                           @RequestBody
                                                   Student newStudent, HttpServletRequest req) throws URISyntaxException
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        newStudent = studentService.save(newStudent);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{Studentid}").buildAndExpand(newStudent.getStudid()).toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }



    @ApiOperation(value = "Updates a specific student whose id matches the integer in the URL.", notes = "Updates are based on the given JSON data.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student updated", response = void.class),
            @ApiResponse(code = 500, message = "Error updating student in database", response = ErrorDetail.class)
    })
    @PutMapping(value = "/Student/{Studentid}")
    public ResponseEntity<?> updateStudent(
            @RequestBody Student updateStudent,
            @ApiParam(value = "Student Id", required = true)
                @PathVariable long Studentid, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        studentService.update(updateStudent, Studentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @ApiOperation(value = "Deletes a specific student whose id matches the integer in the URL.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student deleted", response = void.class),
            @ApiResponse(code = 500, message = "Error deleting student from database", response = ErrorDetail.class)
    })
    @DeleteMapping("/Student/{Studentid}")
    public ResponseEntity<?> deleteStudentById(
            @ApiParam(value = "Student Id", required = true)
                @PathVariable long Studentid, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        studentService.delete(Studentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
