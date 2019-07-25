//package com.lambdaschool.school;
//
//import com.lambdaschool.school.model.Course;
//import com.lambdaschool.school.model.Instructor;
//import com.lambdaschool.school.repository.CourseRepository;
//import com.lambdaschool.school.repository.InstructorRepository;
//import com.lambdaschool.school.repository.StudentRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@Component
//public class SeedData
//{
//    private CourseRepository courserepo;
//    private InstructorRepository instructrepo;
//    private StudentRepository studrepo;
//
//    public SeedData(CourseRepository courserepo, InstructorRepository instructrepo, StudentRepository studrepo)
//    {
//        this.courserepo = courserepo;
//        this.instructrepo = instructrepo;
//        this.studrepo = studrepo;
//    }
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//        Instructor instruct1 = new Instructor("Sally");
//        Instructor instruct2 = new Instructor("Lucy");
//        Instructor instruct3 = new Instructor("Charlie");
//
//        instructrepo.save(instruct1);
//        instructrepo.save(instruct2);
//        instructrepo.save(instruct3);
//
//        Course course1 = new Course("Data Science", instruct1);
//        Course course2 = new Course("Javascript", instruct1);
//        Course course3 = new Course("Node.js", instruct1);
//        Course course4 = new Course("Java Backend", instruct2);
//        Course course5 = new Course("Mobile Android", instruct2);
//        Course course6 = new Course("Mobile iOS", instruct3);
//    }
//}
