package com.lambdaschool.school.service;

import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructrepos;

    @Override
    public List<Instructor> getAllInstructors()
    {
        List<Instructor> rtnList = new ArrayList<>();
        instructrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Instructor getInstructorById(long id) throws ResourceNotFoundException
    {
        return instructrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public Instructor getInstructorByName(String name)
    {
        return null;
    }
}
