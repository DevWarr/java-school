package com.lambdaschool.school;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolApplicationTests
{

    @Test
    public void contextLoads()
    {

    }

    public static void main(String[] args)
    {
        SpringApplication.run(SchoolApplication.class, args);
    }

}
