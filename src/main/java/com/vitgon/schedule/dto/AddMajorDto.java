package com.vitgon.schedule.dto;

import com.vitgon.schedule.annotation.validation.EnumMatch;
import com.vitgon.schedule.annotation.validation.Latin;
import com.vitgon.schedule.annotation.validation.UniqueMajor;

import javax.validation.constraints.*;

public class AddMajorDto {

	public AddMajorDto() {
	}

	public AddMajorDto(int schoolId, String name, int duration, String degree) {
		this.schoolId = schoolId;
		this.name = name;
		this.duration = duration;
		this.degree = degree;
	}

	@Min(value = 1, message = "{NotEmpty.default}")
	private int schoolId;
	
	@UniqueMajor(message = "{Duplicate.major}")
	@NotEmpty(message = "{NotEmpty.default}")
	@Size(min = 5, max = 40, message = "{Size.default}")
	@Latin(message = "{Latin.default}")
	private String name;
	
	@Min(value = 1, message = "{NotEmpty.default}")
	@Max(value = 10, message = "{Max.default}")
	private int duration;
	
	@NotNull(message = "{NotNull.default}")
	@EnumMatch(enumClazz = DegreeEnum.class, message = "{Degree.noMatch}")
	private String degree;

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
}
