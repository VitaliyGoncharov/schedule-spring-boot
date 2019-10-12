package com.vitgon.schedule.service.database.impl.translation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.translation.SchoolTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;
import com.vitgon.schedule.service.database.translation.SchoolTranslationService;

@Service
@Transactional
public class SchoolTranslationServiceImpl implements SchoolTranslationService {

	private final SchoolTranslationDao schoolTranslDao;

	@Autowired
	public SchoolTranslationServiceImpl(SchoolTranslationDao schoolTranslRep) {
		this.schoolTranslDao = schoolTranslRep;
	}
	
	@Override
	public SchoolTranslation save(SchoolTranslation obj) {
		return schoolTranslDao.save(obj);
	}

	@Override
	public SchoolTranslation update(SchoolTranslation obj) {
		return schoolTranslDao.save(obj);
	}

	@Override
	public Optional<SchoolTranslation> findById(SchoolTranslationId id) {
		return schoolTranslDao.findById(id);
	}

	@Override
	public List<SchoolTranslation> findAll() {
		return schoolTranslDao.findAll();
	}
	
	@Override
	public void delete(SchoolTranslation obj) {
		schoolTranslDao.delete(obj);
	}

	@Override
	public void deleteById(SchoolTranslationId id) {
		schoolTranslDao.deleteById(id);
	}

	@Override
	public Optional<SchoolTranslation> findByLocaleIdAndSchoolId(Integer localeId, Integer schoolId) {
		return schoolTranslDao.findByLocaleIdAndSchoolId(localeId, schoolId);
	}

	@Override
	public Integer save(Integer schoolId, Integer localeId, String translation) {
		return schoolTranslDao.save(schoolId, localeId, translation);
	}

	@Override
	public void deleteBySchoolIdAndLocaleId(Integer schoolId, Integer localeId) {
		schoolTranslDao.deleteBySchoolIdAndLocaleId(schoolId, localeId);
	}
}
