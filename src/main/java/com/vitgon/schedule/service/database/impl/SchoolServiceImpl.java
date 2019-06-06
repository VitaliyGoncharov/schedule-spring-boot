package com.vitgon.schedule.service.database.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.SchoolDao;
import com.vitgon.schedule.dao.translation.SchoolTranslationDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.service.database.SchoolService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {
	
	private final SchoolDao schoolDao;
	private final SchoolTranslationDao schoolTranslDao;
	
	@Override
	public School save(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public School update(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public Optional<School> findById(Integer id) {
		return schoolDao.findById(id);
	}

	@Override
	public List<School> findAll() {
		return schoolDao.findAll();
	}

	@Override
	public School findByTitle(String title) {
		SchoolTranslation schoolTransl = schoolTranslDao.findByTitle(title);
		return schoolTransl.getSchool();
	}

	@Override
	public List<School> findAllByLocale(Locale locale) {
		return schoolDao.findAllByLocale(locale);
	}
	
	@Override
	public void delete(School obj) {
		schoolDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		schoolDao.deleteById(id);
	}
	
	@Override
	public School findByName(String name) {
		return schoolDao.findByName(name);
	}
}
