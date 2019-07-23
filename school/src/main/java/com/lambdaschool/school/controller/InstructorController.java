package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.ErrorDetail;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.InstructorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("instructors")
public class InstructorController
{
    private static final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    InstructorService instructorService;


    @ApiOperation(value = "Returns all Instructors listed on one page", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All instructors Found", responseContainer = "List"),
            @ApiResponse(code = 404, message = "Instructors not found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllInstructors(HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        List<Instructor> rtnList = instructorService.getAllInstructors();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }



    @ApiOperation(value = "Returns a specific instructor whose id matches the integer in the URL.", response = Instructor.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instructor found", response = Instructor.class),
            @ApiResponse(code = 404, message = "No instructor found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/id/{id}", produces = {"application/json"})
    public ResponseEntity<?> getInstructorById(@ApiParam(value = "Instructor Id", required = true)
                                                   @PathVariable long id,
                                               HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        return new ResponseEntity<>(instructorService.getInstructorById(id), HttpStatus.OK);
    }



    @ApiOperation(value = "Returns a specific instructor whose name matches the string in the URL.", response = Instructor.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instructor found", response = Instructor.class),
            @ApiResponse(code = 404, message = "No instructor found", response = ErrorDetail.class)
    })
    @GetMapping(value = "/name/{name}", produces = {"application/json"})
    public ResponseEntity<?> getInstructorByName(@ApiParam(value = "Instructor name", required = true)
                                                     @PathVariable String name,
                                                 HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        List<Instructor> rtnList = instructorService.getInstructorByName(name);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }



    @ApiOperation(value = "Adds a new instructor to the database using the given JSON data.", notes = "No need to add courses when creating an instructor; use \"/courses/new/{instructorId}\" to add a new course.\nLocation header links to the student's id URL", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Student created", response = void.class),
            @ApiResponse(code = 500, message = "Unable to add student into database", response = ErrorDetail.class)
    })
    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<?> addNewInstructor(//@ApiParam(value = "New instructor Object", required = true)
                                                  @Valid
                                                  @RequestBody Instructor instr,
                                              HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        instr = instructorService.save(instr);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newInstructorURI = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("instructors/id/{id}").buildAndExpand(instr.getInstructid()).toUri();
        responseHeaders.setLocation(newInstructorURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }



    @ApiOperation(value = "Updates a specific instructor whose id matches the integer in the URL.", notes = "Updates are based on the given JSON data.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instructor updated", response = void.class),
            @ApiResponse(code = 500, message = "Error updating instructor in database", response = ErrorDetail.class)
    })
    @PutMapping(value = "/update/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateInstructor(//@ApiParam(value = "Updated instructor Object", required = true)
                                                  @RequestBody Instructor instr,
                                              @ApiParam(value = "Instructor Id", required = true)
                                                  @PathVariable long id,
                                              HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        instructorService.update(instr, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @ApiOperation(value = "Deletes a specific instructor whose id matches the integer in the URL.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Instructor deleted", response = void.class),
            @ApiResponse(code = 500, message = "Error deleting instructor from database", response = ErrorDetail.class)
    })
    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<?> deleteInstructor(@ApiParam(value = "Instructor Id", required = true)
                                                  @PathVariable long id,
                                              HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        instructorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
