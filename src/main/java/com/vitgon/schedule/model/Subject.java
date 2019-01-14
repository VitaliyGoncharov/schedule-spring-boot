package com.vitgon.schedule.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vitgon.schedule.model.translation.SubjectTranslation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"schedules"})
@Entity
@Table(name = "subjects")
public class Subject extends BaseModel<Integer> {
	
	@JsonIgnore
	@OneToMany(mappedBy = "subject")
	private List<Schedule> schedules = new ArrayList<>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "subjectTranslationId.subject")
	private List<SubjectTranslation> subjectTranslations = new ArrayList<>();

	public Subject(List<Schedule> schedules, List<SubjectTranslation> subjectTranslations) {
		this.schedules = schedules;
		this.subjectTranslations = subjectTranslations;
	}
}
