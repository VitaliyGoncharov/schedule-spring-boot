package com.vitgon.schedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"group"})
@Entity
@Table(name = "schedules")
public class Schedule extends BaseModel<Integer> {
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	@Column(name = "day_num")
	private int dayNum;
	
	@Column(name = "week_type")
	private int week;
	
	@Column(name = "subject_num")
	private int subjectNum;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	@Column(name = "lesson_type")
	private int lessonType;
	
	@Column(name = "classroom")
	private String classroom;
}
