package com.msref.topic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.msref.topic.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer>{

	public List<Topic> findByCourseId(Integer courseId);
	
	public List<Topic> deleteByCourseId(Integer courseId);
	
    @Query("SELECT count(t.id) FROM Topic t where t.courseId = :courseId") 
    Integer topicCountByCourseId(@Param("courseId") Integer courseId);
}
