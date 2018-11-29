package com.msref.course.hystrix;

import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HystrixDeleteTopicsRequestCommand extends HystrixCommand<Integer>{

	RestTemplate restTemplate;
	
	Integer courseId;

	public HystrixDeleteTopicsRequestCommand(Integer courseId, RestTemplate restTemplate) {
		super(HystrixCommandGroupKey.Factory.asKey("TopicServiceGroup"));
		this.courseId = courseId;
		this.restTemplate = restTemplate;
	}

	@Override
	protected Integer run() throws Exception {
		restTemplate.delete("http://topic-service/api/courses/" + courseId +"/topics");
		
		// If the run method successfully completes, 1 will be returned. If required client can read this to check whether the execution of Hystrix command is successful or not
		return 1;
	}
	
	@Override
	protected Integer getFallback() {
		
		// Due to some error, if the fallback method is invoked, 0 will be returned. If required client can read this to check whether the execution of Hystrix command is successful or not
		return 0;
	}

}
