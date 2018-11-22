package com.msref.topic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msref.topic.exception.EntityNotFoundException;
import com.msref.topic.model.Topic;
import com.msref.topic.repository.TopicRepository;
import com.msref.topic.service.TopicService;
import com.msref.topic.util.GlobalErrorHandlingConstants;

@Service
@Transactional
public class TopicServiceImpl implements TopicService{

	@Autowired
	TopicRepository topicRepository;
	
	@Override
	public List<Topic> createTopics(List<Topic> topicList) {
		return topicRepository.saveAll(topicList);
	}

	@Override
	public Topic updateTopic(Topic topic) {
		if (!topicRepository.existsById(topic.getTopicId())) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		return topicRepository.save(topic);
	}

	@Override
	public void deleteTopic(Integer topicId) {
		if (!topicRepository.existsById(topicId)) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		topicRepository.deleteById(topicId);		
	}
	
	@Override
	public void deleteTopicsByCourse(Integer courseId) {
		if (topicRepository.topicCountByCourseId(courseId) == 0) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		topicRepository.deleteByCourseId(courseId);
	}	

	@Override
	public Topic getTopic(Integer topicId) {
		if (!topicRepository.existsById(topicId)) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		return topicRepository.findById(topicId).get();
	}

	@Override
	public List<Topic> listTopics() {
		return topicRepository.findAll();
	}

	@Override
	public List<Topic> getTopicsByCourse(Integer courseId) {
		List<Topic> topicList = topicRepository.findByCourseId(courseId);
		return topicList;
	}

}
