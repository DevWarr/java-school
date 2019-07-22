package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Instructor;

import java.util.List;

public interface InstructorService
{
    List<Instructor> getAllInstructors();

    Instructor getInstructorById(long id);

    Instructor getInstructorByName(String name);
}
