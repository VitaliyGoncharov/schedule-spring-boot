package com.vitgon.schedule.controller.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.vitgon.schedule.dto.AddSubjectTranslationDTO;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Subject;
import com.vitgon.schedule.model.database.translation.SubjectTranslation;
import com.vitgon.schedule.model.database.translation.pk.SubjectTranslationId;
import com.vitgon.schedule.sequence.TranslationValidationSequence;
import com.vitgon.schedule.service.database.LocaleService;
import com.vitgon.schedule.service.database.SubjectService;
import com.vitgon.schedule.service.database.translation.SubjectTranslationService;

@Controller
public class SubjectTranslationController {
	
	private SubjectService subjectService;
	private LocaleService localeService;
	private SubjectTranslationService subjectTranslationService;
	
	@Autowired
	public SubjectTranslationController(SubjectService subjectService,
										   LocaleService localeService,
										   SubjectTranslationService subjectTranslationService) {
		this.subjectService = subjectService;
		this.localeService = localeService;
		this.subjectTranslationService = subjectTranslationService;
	}

	@PostMapping("/control/subject/translation/add")
	public RedirectView addSubjectTranslation(@Validated(TranslationValidationSequence.class) AddSubjectTranslationDTO addSubjectTranslationDTO,
											  BindingResult result,
											  RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("activeTab", "addSubjectTranslation");
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSubjectTranslationDTO", result);
			redirectAttributes.addFlashAttribute("addSubjectTranslationDTO", addSubjectTranslationDTO);
			return new RedirectView("/control");
		}
		
		Subject subject = subjectService.findById(addSubjectTranslationDTO.getSubjectId());
		Locale locale = localeService.findById(addSubjectTranslationDTO.getLocaleId());
		
		subjectTranslationService.save(new SubjectTranslation(
				new SubjectTranslationId(subject, locale),
				addSubjectTranslationDTO.getTitle()
		));
		
		redirectAttributes.addFlashAttribute("addSubjectTranslationSuccess", true);
		
		return new RedirectView("/control");
	}
	
}
