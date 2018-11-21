package com.msref.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msref.course.model.ErrorResponse;

@Configuration
public class CourseConfig {
	
	@Bean
	public ErrorResponse errorResponse() {
		return new ErrorResponse();
	}
}
