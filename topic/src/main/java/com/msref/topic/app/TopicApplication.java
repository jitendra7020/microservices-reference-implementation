package com.msref.topic.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.msref.topic")
@EntityScan("com.msref.topic.model")
@EnableJpaRepositories(basePackages = "com.msref.topic.repository")
public class TopicApplication {

	public static void main (String [] args) {
		SpringApplication.run(TopicApplication.class, args);
	}
}
