package com.vitgon.schedule.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vitgon.schedule.dto.SubjectDto;
import com.vitgon.schedule.projection.SubjectProjection;
import com.vitgon.schedule.service.database.SubjectService;

import lombok.AllArgsConstructor;

@Component
public class SubjectDtoService {
	
	private SubjectService subjectService;

	public SubjectDtoService(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public List<SubjectDto> getSubjectDtoList() {
		List<SubjectProjection> subjects = subjectService.findAllByBrowserDefaultLocale();
		List<SubjectDto> subjectDtoList = new ArrayList<>();
		for (SubjectProjection subject : subjects) {
			SubjectDto subjectDto = new SubjectDto();
			subjectDto.setId(subject.getId());
			subjectDto.setName(subject.getTranslation());
			subjectDtoList.add(subjectDto);
		}
		return subjectDtoList;
	}
	
	public List<SubjectDto> getSubjectDtoListForAdminPanel() {
		List<SubjectProjection> subjects = subjectService.findAllByBrowserDefaultLocale();
		List<SubjectDto> subjectDtoList = new ArrayList<>();
		for (SubjectProjection subject : subjects) {
			SubjectDto subjectDto = new SubjectDto();
			subjectDto.setId(subject.getId());
			subjectDto.setName(subject.getName());
			subjectDto.setTranslation(subject.getTranslation());
			subjectDtoList.add(subjectDto);
		}
		return subjectDtoList;
	}
	
	public List<SubjectDto> getSubjectDtoListByLocaleId(Integer localeId) {
		List<SubjectProjection> subjects = subjectService.findAllByLocaleId(localeId);
		List<SubjectDto> subjectDtoList = new ArrayList<>();
		for (SubjectProjection subject : subjects) {
			SubjectDto subjectDto = new SubjectDto();
			subjectDto.setId(subject.getId());
			subjectDto.setName(subject.getTranslation());
			subjectDtoList.add(subjectDto);
		}
		return subjectDtoList;
	}
	
	public List<SubjectDto> getSubjectDtoListByLocaleIdForAdminPanel(Integer localeId) {
		List<SubjectProjection> subjects = subjectService.findAllByLocaleId(localeId);
		List<SubjectDto> subjectDtoList = new ArrayList<>();
		for (SubjectProjection subject : subjects) {
			SubjectDto subjectDto = new SubjectDto();
			subjectDto.setId(subject.getId());
			subjectDto.setName(subject.getName());
			subjectDto.setTranslation(subject.getTranslation());
			subjectDtoList.add(subjectDto);
		}
		return subjectDtoList;
	}
}
