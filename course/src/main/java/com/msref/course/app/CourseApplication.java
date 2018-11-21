package com.msref.course.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.msref.course")
@EnableJpaRepositories(basePackages = "com.msref.course.repository")
@EntityScan("com.msref.course.model")
public class CourseApplication {

	public static void main (String [] args) {
		SpringApplication.run(CourseApplication.class, args);
	}
}
