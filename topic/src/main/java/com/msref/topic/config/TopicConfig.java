package com.msref.topic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msref.topic.model.ErrorResponse;

@Configuration
public class TopicConfig {

	@Bean
	public ErrorResponse errorResponse() {
		return new ErrorResponse();
	}
}
