package com.msref.course.controller;

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

import com.msref.course.model.Course;
import com.msref.course.model.Topic;
import com.msref.course.service.CourseService;
import com.msref.course.util.CourseUtil;

@RestController
@RequestMapping("/api")
public class CourseController {
	
	/**
	 * Logger object
	 */
	private static Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/courses")
	@ResponseStatus(code = HttpStatus.OK)
	List<Course> listCourses(){
		logger.info("Request received for listCourses operation");
		
		return courseService.listCourses();
	}
	
	@GetMapping("/courses/{courseId}")
	@ResponseStatus(code = HttpStatus.OK)
	Course getCourse(@PathVariable(value = "courseId") Integer courseId){
		logger.info("Request received for getCourse operation");
		
		return courseService.getCourse(courseId);
	}
	
	@PostMapping("/courses")
	@ResponseStatus(code = HttpStatus.CREATED)
	Course createCourse(@Valid @RequestBody Course course){
		logger.info("Request received for createCourse operation");
		
		// Adding the courseId to each item in topic list
		List<Topic> modifiedTopicList = CourseUtil.addCourseIdToTopicList(course.getTopics(), course.getCourseId());
		course.setTopics(modifiedTopicList);
		
		return courseService.createCourse(course);
	}
	
	@PutMapping("/courses/{courseId}")
	@ResponseStatus(code = HttpStatus.OK)
	Course updateCourse(@Valid @RequestBody Course course, @PathVariable(value = "courseId") Integer courseId){
		logger.info("Request received for updateCourse operation");
		
		course.setCourseId(courseId);
		return courseService.updateCourse(course);
	}
	
	@DeleteMapping("/courses/{courseId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	void deleteCourse(@PathVariable(value = "courseId") Integer courseId){
		logger.info("Request received for deleteCourse operation");
		
		courseService.deleteCourse(courseId);
	}	
 	
}
