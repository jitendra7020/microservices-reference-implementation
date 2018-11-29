package com.msref.course.util;

import java.util.List;

import com.msref.course.model.Topic;

public class CourseUtil {

	/**
	 * This method is used to add a particular course Id to a list of topic objects
	 * @param topicList
	 * @param courseId
	 * @return
	 */
	public static List<Topic> addCourseIdToTopicList(List<Topic> topicList, Integer courseId) {
		for (Topic topic : topicList) {
			if (topic == null) {
				continue;
			}
			topic.setCourseId(courseId);
		}
		
		return topicList;
	}
}
