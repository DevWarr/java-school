package com.lambdaschool.school.service;

import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructrepos;

    @Override
    public ArrayList<Instructor> getAllInstructors()
    {
        ArrayList<Instructor> rtnList = new ArrayList<>();
        instructrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Instructor getInstructorById(long id) throws ResourceNotFoundException
    {
        return instructrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public ArrayList<Instructor> getInstructorByName(String name) throws ResourceNotFoundException
    {
        ArrayList<Instructor> rtnInstructorList = new ArrayList<>();
        instructrepos.findInstructorsByInstructnameEquals(name).iterator().forEachRemaining(rtnInstructorList::add);
        return rtnInstructorList;
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (instructrepos.findById(id).isPresent())
        {
            instructrepos.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Instructor save(Instructor instructor)
    {
        Instructor rtnInstr = new Instructor();
        rtnInstr.setInstructname(instructor.getInstructname());
        return instructrepos.save(rtnInstr);
    }

    @Override
    public Instructor update(Instructor instructor, long id) throws ResourceNotFoundException
    {
        Instructor rtnInstr = instructrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (instructor.getInstructname() != null)
        {
            rtnInstr.setInstructname(instructor.getInstructname());
        }

        return instructrepos.save(rtnInstr);
    }
}
