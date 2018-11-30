package com.msref.course.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
@Table (name = "course")
public class Course {
	
	@Id
	@Column(name = "id")
	private Integer courseId;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String lecturer;
	
	@Transient
	@Valid //Topic is a  nested object in Course. If you don't use @valid annotation here, only the course fileds will be validated even in you have used the @NotBland annotation on topic fields
	private List<Topic> topics;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

}
