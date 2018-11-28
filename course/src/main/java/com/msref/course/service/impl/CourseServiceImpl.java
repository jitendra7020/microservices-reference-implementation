package com.msref.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msref.course.exception.EntityAlreadyExistsException;
import com.msref.course.exception.EntityNotFoundException;
import com.msref.course.hystrix.TopicGetTopicsRequestCommand;
import com.msref.course.model.Course;
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
		
		return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(Course course) {
		if (!courseRepository.existsById(course.getCourseId())) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		return courseRepository.save(course);
	}

	@Override
	public void deleteCourse(Integer courseId) {
		if (!courseRepository.existsById(courseId)) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		courseRepository.deleteById(courseId);
	}

	@Override
	public Course getCourse(Integer courseId) {
		
		if (!courseRepository.existsById(courseId)) {
			throw new EntityNotFoundException(GlobalErrorHandlingConstants.RESOURCE_NOT_FOUND_ERROR_MESSAGE, GlobalErrorHandlingConstants.RESOURCE_ALREADY_EXISTS_ERROR_CODE);
		}
		
		Course course = courseRepository.findById(courseId).get();
		
		// Retrieving and setting the corresponding topics for the course
		// An Hystrix command is used to invoke the topic service and retrieve the list of topics 
		course.setTopics(new TopicGetTopicsRequestCommand(course.getCourseId(), restTemplate).execute());
		return course;
	}

	@Override
	public List<Course> listCourses() {
		List<Course> courseList = courseRepository.findAll();
		
		for (Course course : courseList) {
			// Retrieving and setting the corresponding topics for the course
			// An Hystrix command is used to invoke the topic service and retrieve the list of topics 
			course.setTopics(new TopicGetTopicsRequestCommand(course.getCourseId(), restTemplate).execute());			
		}
		return courseList;
	}

}