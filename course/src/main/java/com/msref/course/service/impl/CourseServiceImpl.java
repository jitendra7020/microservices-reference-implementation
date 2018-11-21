package com.msref.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msref.course.exception.EntityAlreadyExistsException;
import com.msref.course.exception.EntityNotFoundException;
import com.msref.course.model.Course;
import com.msref.course.repository.CourseRepository;
import com.msref.course.service.CourseService;
import com.msref.course.util.GlobalErrorHandlingConstants;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepository;
	
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
		
		return courseRepository.findById(courseId).get();
	}

	@Override
	public List<Course> listCourses() {
		return courseRepository.findAll();
	}

}
