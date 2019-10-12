package com.vitgon.schedule.controller.rest.adminpanel;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.annotation.validation.LocaleExists;
import com.vitgon.schedule.dto.AddSubjectDto;
import com.vitgon.schedule.dto.EditSubjectDto;
import com.vitgon.schedule.dto.SubjectDto;
import com.vitgon.schedule.model.ApiError;
import com.vitgon.schedule.model.ApiSuccess;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.service.MessageService;
import com.vitgon.schedule.service.SubjectDtoService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.view.Views;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/control/subjects")
public class SubjectRestControllerAdminPanel {
	
	private SubjectDtoService subjectDtoService;
	private SubjectService subjectService;
	private MessageService messageService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SubjectDto addSubject(@RequestBody @Valid AddSubjectDto addSubjectDto) {
		Subject subject = subjectService.save(new Subject(addSubjectDto.getSubjectName().toLowerCase()));
		return convertToDto(subject);
	}
	
	@PutMapping("/{subjectId}")
	@ResponseStatus(HttpStatus.OK)
	public ApiSuccess updateSubject(@RequestBody @Valid EditSubjectDto editSubjectDto) {
		Optional<Subject> subjectOpt = subjectService.findById(editSubjectDto.getId());
		if (!subjectOpt.isPresent()) {
			throw new IllegalArgumentException("Subject with such name doesn't exist!");
		}
		Subject subject = subjectOpt.get();
		subject.setName(editSubjectDto.getName());
		subjectService.update(subject);
		return new ApiSuccess(new Date(), "You successfully edited subject!");
	}
	
	@DeleteMapping(params = {"id"})
	public ResponseEntity<?> deleteSubject(@RequestParam("id") Subject subject) {
		if (subject == null) {
			Map<String, List<String>> errors = new HashMap<>();
			errors.put("id", Arrays.asList(messageService.getMessage("chooseValue")));
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiError(new Date(), "Subject Not Found", errors));
		}
		subjectService.delete(subject);
		return ResponseEntity.ok(new ApiSuccess(new Date(), "You successfully removed subject!"));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	@JsonView(Views.AdminPanel.class)
	public List<SubjectDto> getSubjectsWithView() {
		return subjectDtoService.getSubjectDtoListForAdminPanel();
	}
	
	@GetMapping("/locale-id/{localeId}")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(Views.AdminPanel.class)
	public List<SubjectDto> getSubjectsWithView(@PathVariable @LocaleExists Integer localeId) {
		return subjectDtoService.getSubjectDtoListByLocaleIdForAdminPanel(localeId);
	}
	
	private SubjectDto convertToDto(Subject subject) {
		SubjectDto subjectDto = new SubjectDto();
		subjectDto.setId(subject.getId());
		subjectDto.setName(subject.getName());
		return subjectDto;
	}
}
