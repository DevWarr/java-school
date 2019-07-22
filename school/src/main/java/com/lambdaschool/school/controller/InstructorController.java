package com.lambdaschool.school.controller;

import com.lambdaschool.school.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

        return new ResponseEntity<>(instructorService.getAllInstructors(), HttpStatus.OK);
    }


}
