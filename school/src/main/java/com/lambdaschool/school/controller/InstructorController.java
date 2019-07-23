package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.InstructorService;
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

    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllInstructors(HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        List<Instructor> rtnList = instructorService.getAllInstructors();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}", produces = {"application/json"})
    public ResponseEntity<?> getInstructorById(@PathVariable long id, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");

        return new ResponseEntity<>(instructorService.getInstructorById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = {"application/json"})
    public ResponseEntity<?> getInstructorByName(@PathVariable String name, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        List<Instructor> rtnList = instructorService.getInstructorByName(name);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @PostMapping(value = "/new", consumes = {"application/json"})
    public ResponseEntity<?> addNewInstructor(@Valid @RequestBody Instructor instr, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        instr = instructorService.save(instr);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newInstructorURI = ServletUriComponentsBuilder.fromCurrentRequest().replacePath("instructors/id/{id}").buildAndExpand(instr.getInstructid()).toUri();
        responseHeaders.setLocation(newInstructorURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateInstructor(@RequestBody Instructor instr, @PathVariable long id, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        instructorService.update(instr, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/{id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable long id, HttpServletRequest req)
    {
        logger.info(req.getMethod().toUpperCase() + " \"" + req.getRequestURI() + "\" accessed.");
        instructorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
