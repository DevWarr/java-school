package com.lambdaschool.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc // Needed if we want to use OUR custom error messages instead of Spring's
@EnableJpaAuditing
@SpringBootApplication
public class SchoolApplication
{

    public static void main(String[] args)
    {

        ApplicationContext ctx = SpringApplication.run(SchoolApplication.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet"); // Required spelling of "dispatcherServlet"
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
