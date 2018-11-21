package com.msref.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msref.course.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
