package com.lambdaschool.school.service;

import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.CourseRepository;
import com.lambdaschool.school.repository.InstructorRepository;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service(value = "courseService")
public class CourseServiceImpl implements CourseService
{
    @Autowired
    private CourseRepository courserepos;

    @Autowired
    private InstructorRepository instructorrepos;

    @Override
    public ArrayList<Course> findAll()
    {
        ArrayList<Course> list = new ArrayList<>();
        courserepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public ArrayList<CountStudentsInCourses> getCountStudentsInCourse()
    {
        return courserepos.getCountStudentsInCourse();
    }

    @Transactional
    @Override
    public Course save(Course newCourse, long id) throws ResourceNotFoundException
    {
        Instructor courseInstr = instructorrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
        Course rtnCourse = new Course();
        rtnCourse.setCoursename(newCourse.getCoursename());
        rtnCourse.setInstructor(courseInstr);
        return courserepos.save(rtnCourse);
    }

    @Transactional
    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (courserepos.findById(id).isPresent())
        {
            courserepos.deleteCourseFromStudcourses(id);
            courserepos.deleteById(id);
        } else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }
    }
}
