package com.msref.topic.controller;

import java.util.List;

import javax.validation.Valid;

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
	
	@Autowired
	TopicService topicService;

	@GetMapping("/courses/topics/{topicId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Topic getTopic(@PathVariable(value = "topicId") Integer topicId) {
		return topicService.getTopic(topicId);
	}
	
	@GetMapping("/courses/{courseId}/topics")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Topic> getTopicsByCourse(@PathVariable(value = "courseId") Integer courseId) {
		return topicService.getTopicsByCourse(courseId);
	}	
	
	@GetMapping("/courses/topics")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Topic> listTopics() {
		return topicService.listTopics();
	}
	
	@PostMapping("/courses/{courseId}/topics")
	@ResponseStatus(code = HttpStatus.CREATED)
	public List<Topic> createTopics(@Valid @RequestBody List<Topic> topicList, @PathVariable (value = "courseId") Integer courseId) {
		topicList = TopicUtil.addCourseIdToTopicList(topicList, courseId);
		return topicService.createTopics(topicList);
	}
	
	@PutMapping("/courses/topics/{topicId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Topic updateTopic(@Valid @RequestBody Topic topic, @PathVariable (value = "topicId") Integer topicId) {
		topic.setTopicId(topicId);
		return topicService.updateTopic(topic);
	}
	
	@DeleteMapping("/courses/topics/{topicId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteTopic(@PathVariable(value = "topicId") Integer topicId) {
		topicService.deleteTopic(topicId);
	}
	
	@DeleteMapping("/courses/{courseId}/topics")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteTopicsByCourse(@PathVariable(value = "courseId") Integer courseId) {
		topicService.deleteTopicsByCourse(courseId);
	}	
	
}
