package com.vitgon.schedule.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class EditScheduleResponseDTO implements Serializable {
	private static final long serialVersionUID = -6993176968171153486L;
	
	private int id;
	private int subjectId;
	private String subjectTitle;
	private int dayNum;
	private String week;
	private int lessonNum;
	private String teacherName;
	private String lessonType;
	private String lessonTypeName;
	private String classroom;
}
