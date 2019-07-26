package com.lambdaschool.school.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{
    @Autowired
    private Environment env;

    @Bean(name = "dsCustom")
    public DataSource dataSource()
    {
        String myUrlString;
        String myDriverClass;
        String myDBUser;
        String myDBPassword;

        // H2 = testing
        // PostgreSQL = production
//        String dbValue = env.getProperty("local.run.db");
        String dbValue = "POSTGRESQL"; // Trying to get the value from application.properties causes a nullPointerException >.>

        if(dbValue.equalsIgnoreCase("POSTGRESQL"))
        {
            // SQL setup:

            myUrlString = "jdbc:postgresql://" + System.getenv("MYDBHOST") + ":5432/" + System.getenv("MYDBNAME");
            myDriverClass = "org.postgresql.Driver";

            // Environment variables are System.getenv
            myDBUser = System.getenv("MYDBUSER");
            myDBPassword = System.getenv("MYDBPASSWORD");
        }
        else
        {
            // assume H2 database
            myUrlString = "jdbc:h2:mem:testdb";
            myDriverClass = "org.h2.Driver";
            myDBUser = "sa";
            myDBPassword = "";
        }

        System.out.println(myUrlString);
        System.out.println(myDBUser);
        System.out.println(myDBPassword);
        return DataSourceBuilder.create().username(myDBUser).password(myDBPassword).url(myUrlString).driverClassName(myDriverClass).build();
    }

    @Bean(name = "jdbcCustom")
    @Autowired
    public JdbcTemplate jdbcTemplate(@Qualifier("dsCustom") DataSource dsCustom)
    {
        return new JdbcTemplate(dsCustom);
    }
}
