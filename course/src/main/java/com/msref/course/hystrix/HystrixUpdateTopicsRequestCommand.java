package com.msref.course.hystrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.github.andrewoma.dexx.collection.HashMap;
import com.github.andrewoma.dexx.collection.Map;
import com.msref.course.model.Topic;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HystrixUpdateTopicsRequestCommand extends HystrixCommand<List<Topic>>{

	RestTemplate restTemplate;
	
	List<Topic> topics;
	
	Integer courseId;
	
	public HystrixUpdateTopicsRequestCommand(List<Topic> topics, Integer courseId, RestTemplate restTemplate) {
		super(HystrixCommandGroupKey.Factory.asKey("TopicServiceGroup"));
		this.topics = topics;
		this.courseId = courseId;
		this.restTemplate = restTemplate;
	}	
	
	@Override
	protected List<Topic> run() throws Exception {

		//rest template doesn't have a putforobject method which returns a response for the put operations. Therefore the exchange method is used in the rest template.
		HttpEntity<List<Topic>> topicHttpEntity = new HttpEntity<List<Topic>>(topics);
		Map<String, String> uriVariable = new HashMap<>();
		ResponseEntity<Topic[]> response = restTemplate.exchange("http://topic-service/api/courses/" + courseId +"/topics", HttpMethod.PUT, topicHttpEntity, Topic[].class, uriVariable);

		return Arrays.asList(response.getBody());
	}
	
	@Override
	protected List<Topic> getFallback() {
		List<Topic> emptyTopicList = new ArrayList<>(); 
		return emptyTopicList;
	}	
}
