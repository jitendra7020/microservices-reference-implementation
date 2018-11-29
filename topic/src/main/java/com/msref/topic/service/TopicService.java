package com.msref.topic.service;

import java.util.List;

import com.msref.topic.model.Topic;

public interface TopicService {
	
	/**
	 * Service method to create multiple topics under a given course
	 * @param topic
	 * @return
	 */
	public List<Topic> createTopics(List<Topic> topicList);
	
	/**
	 * Service method to update a topic
	 * @param topic
	 * @return
	 */
	public Topic updateTopic(Topic topic);
	
	/**
	 * Service method used to drop all existing topics pertaining to a course and add the new topic list
	 * @param courseId
	 * @param topicList
	 * @return
	 */
	public List<Topic> updateTopicByCourse(Integer courseId, List<Topic> topicList);
	
	/**
	 * Service method to delete a topic
	 * @param topicId
	 */
	public void deleteTopic(Integer topicId);
	
	/**
	 * Delete all topics pertaining to a course
	 * @param courseId
	 */
	public void deleteTopicsByCourse(Integer courseId);	
	
	/**
	 * Service method to retrieve a single topic
	 * @param topicId
	 * @return
	 */
	public Topic getTopic(Integer topicId);
	
	/**
	 * Service method to retrieve a list of topics by course Id
	 * @param courseId
	 * @return
	 */
	public List<Topic> getTopicsByCourse(Integer courseId);
	
	/**
	 * Service method to retrieve a full list of topics
	 * @return
	 */
	public List<Topic> listTopics();
}
