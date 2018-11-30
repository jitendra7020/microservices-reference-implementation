package com.msref.course.model;

import javax.validation.constraints.NotBlank;

public class Topic {
	
	private int topicId;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	private Integer courseId;

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
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

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}

