package com.vitgon.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.SchoolDao;
import com.vitgon.schedule.dao.translation.SchoolTranslationDao;
import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.School;
import com.vitgon.schedule.model.translation.SchoolTranslation;
import com.vitgon.schedule.service.SchoolService;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private final SchoolDao schoolDao;
	
	@Autowired
	private final SchoolTranslationDao schoolTranslDao;
	
	public SchoolServiceImpl(SchoolDao schoolDao, SchoolTranslationDao schoolTranslDao) {
		this.schoolDao = schoolDao;
		this.schoolTranslDao = schoolTranslDao;
	}
	
	@Override
	public School save(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public School update(School obj) {
		return schoolDao.save(obj);
	}

	@Override
	public School findById(Integer id) {
		return schoolDao.findById(id).get();
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
}
