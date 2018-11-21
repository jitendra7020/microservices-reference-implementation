package com.msref.course.service;

import java.util.List;

import com.msref.course.model.Course;

public interface CourseService {

	/**
	 * Service method to create a course
	 * @param course
	 */
	public Course createCourse (Course course);
	
	/**
	 * Service method to update an existing course
	 * @param course
	 */
	public Course updateCourse (Course course);
	
	/**
	 * Service method to delete a course
	 * @param courseId
	 */
	public void deleteCourse (Integer courseId);
	
	/**
	 * service method to retrieve a single course
	 * @param courseId
	 * @return
	 */
	public Course getCourse (Integer courseId);
	
	/**
	 * service method to list all the courses
	 * @return
	 */
	public List<Course> listCourses ();
}
