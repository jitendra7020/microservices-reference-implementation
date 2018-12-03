package com.msref.topic.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.msref.topic.model.Topic;
import com.msref.topic.service.TopicService;
import com.msref.topic.util.TopicUtil;

@RestController
@RequestMapping("/api")
public class TopicController {
	
	/**
	 * Logger object
	 */
	private static Logger logger = LoggerFactory.getLogger(TopicController.class);	
	
	@Autowired
	TopicService topicService;

	@GetMapping("/courses/topics/{topicId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Topic getTopic(@PathVariable(value = "topicId") Integer topicId) {
		logger.info("Request received for getTopic operation");
		
		return topicService.getTopic(topicId);
	}
	
	@GetMapping("/courses/{courseId}/topics")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Topic> getTopicsByCourse(@PathVariable(value = "courseId") Integer courseId) {
		logger.info("Request received for getTopicsByCourse operation");
		
		return topicService.getTopicsByCourse(courseId);
	}	
	
	@GetMapping("/courses/topics")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Topic> listTopics() {
		logger.info("Request received for listTopics operation");
		
		return topicService.listTopics();
	}
	
	// Even though I have used @Valid annotation, it won't work with a list. This is a limitation in bean validations. @Valid works with a 
	// list only if it has been used in the class itself on the attribute which has a list of objects 
	// For example Course class has a list of Topics and @Valid is working fine when used inside the Course class - See Course.java file
	@PostMapping("/courses/{courseId}/topics")
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<Topic> createTopics(@Valid @RequestBody List<Topic> topicList, @PathVariable (value = "courseId") Integer courseId) {
		logger.info("Request received for createTopics operation");
		
		topicList = TopicUtil.addCourseIdToTopicList(topicList, courseId);
		return topicService.createTopics(topicList);
	}
	
	//@Valid is working fine here as there is only one object not a list of objects
	@PutMapping("/courses/topics/{topicId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Topic updateTopic(@Valid @RequestBody Topic topic, @PathVariable (value = "topicId") Integer topicId) {
		logger.info("Request received for updateTopic operation");
		
		topic.setTopicId(topicId);
		return topicService.updateTopic(topic);
	}
	
	// Even though I have used @Valid annotation, it won't work with a list. This is a limitation in bean validations. @Valid works with a 
	// list only if it has been used in the class itself on the attribute which has a list of objects 
	// For example Course class has a list of Topics and @Valid is working fine when used inside the Course class - See Course.java file	
	@PutMapping("/courses/{courseId}/topics")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Topic> updateTopicsByCourse(@Valid @RequestBody List<Topic> topicList, @PathVariable (value = "courseId") Integer courseId) {
		logger.info("Request received for updateTopicsByCourse operation");
		
		topicList = TopicUtil.addCourseIdToTopicList(topicList, courseId);
		return topicService.updateTopicByCourse(courseId, topicList);
	}	
	
	@DeleteMapping("/courses/topics/{topicId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteTopic(@PathVariable(value = "topicId") Integer topicId) {
		logger.info("Request received for deleteTopic operation");
		
		topicService.deleteTopic(topicId);
	}
	
	@DeleteMapping("/courses/{courseId}/topics")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteTopicsByCourse(@PathVariable(value = "courseId") Integer courseId) {
		logger.info("Request received for deleteTopicsByCourse operation");
		
		topicService.deleteTopicsByCourse(courseId);
	}	
	
}
