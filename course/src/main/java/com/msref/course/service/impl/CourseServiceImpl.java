package com.msref.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msref.course.exception.EntityAlreadyExistsException;
import com.msref.course.exception.EntityNotFoundException;
import com.msref.course.hystrix.HystrixAddTopicRequestCommand;
import com.msref.course.hystrix.HystrixDeleteTopicsRequestCommand;
import com.msref.course.hystrix.HystrixGetTopicsRequestCommand;
import com.msref.course.hystrix.HystrixUpdateTopicsRequestCommand;
import com.msref.course.model.Course;
import com.msref.course.model.Topic;
import com.msref.course.repository.CourseRepository;
import com.msref.course.service.CourseService;
import com.msref.course.util.GlobalErrorHandlingConstants;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public Course createCourse(Course course) {
		if (courseRepository.existsById(course.getCourseId())) {
			throw new EntityAlreadyExistsException(GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		// persisting the course in the course table
		Course persistedCourse = courseRepository.save(course);

		// If the course was successfully persisted in the DB, the relevant topics should be added via topics microservice
		if(persistedCourse != null) {
			List<Topic> persistedTopics = new HystrixAddTopicRequestCommand(course.getTopics(), course.getCourseId(), restTemplate).execute();
			persistedCourse.setTopics(persistedTopics);
		}
		
		return persistedCourse;
	}

	@Override
	public Course updateCourse(Course course) {
		if (!courseRepository.existsById(course.getCourseId())) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		//Persisting the new course object
		Course persistedCourse = courseRepository.save(course);
		
		//Persisting the new topics associated with the course object via the topics microservice. This will delete the existing topics pertaining to this course and add the new ones.
		if(persistedCourse != null) {
			List<Topic> persistedTopics = new HystrixUpdateTopicsRequestCommand(course.getTopics(), course.getCourseId(), restTemplate).execute();
			persistedCourse.setTopics(persistedTopics);
		}		
		
		return persistedCourse;
	}

	@Override
	public void deleteCourse(Integer courseId) {
		if (!courseRepository.existsById(courseId)) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		// Deleting the course object from the course table in the DB
		courseRepository.deleteById(courseId);
		
		// Deleting the topics associated with this course via the topics microservice
		HystrixDeleteTopicsRequestCommand deleteCommand = new HystrixDeleteTopicsRequestCommand(courseId, restTemplate);
		deleteCommand.execute();
	}

	@Override
	public Course getCourse(Integer courseId) {
		
		if (!courseRepository.existsById(courseId)) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		Course course = courseRepository.findById(courseId).get();
		
		// Retrieving and setting the corresponding topics for the course
		// An Hystrix command is used to invoke the topic service and retrieve the list of topics 
		course.setTopics(new HystrixGetTopicsRequestCommand(course.getCourseId(), restTemplate).execute());
		return course;
	}

	@Override
	public List<Course> listCourses() {
		List<Course> courseList = courseRepository.findAll();
		
		for (Course course : courseList) {
			// Retrieving and setting the corresponding topics for the course
			// An Hystrix command is used to invoke the topic service and retrieve the list of topics 
			course.setTopics(new HystrixGetTopicsRequestCommand(course.getCourseId(), restTemplate).execute());			
		}
		return courseList;
	}

}