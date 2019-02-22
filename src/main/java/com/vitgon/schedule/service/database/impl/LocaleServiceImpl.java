package com.vitgon.schedule.service.database.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.LocaleDao;
import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.Schedule;
import com.vitgon.schedule.service.database.LocaleService;

@Service
@Transactional
public class LocaleServiceImpl implements LocaleService {

	@Autowired
	private final LocaleDao localeDao;
	
	public LocaleServiceImpl(LocaleDao localeDao) {
		this.localeDao = localeDao;
	}
	
	@Override
	public Locale save(Locale obj) {
		return localeDao.save(obj);
	}

	@Override
	public Locale update(Locale obj) {
		return localeDao.save(obj);
	}

	@Override
	public Locale findById(Integer id) {
		return localeDao.findById(id).orElse(null);
	}

	@Override
	public List<Locale> findAll() {
		return localeDao.findAll();
	}

	@Override
	public Locale findByCode(String code) {
		return localeDao.findByCode(code);
	}
	
	@Override
	public void delete(Locale obj) {
		localeDao.delete(obj);
	}

	@Override
	public void deleteById(Integer id) {
		localeDao.deleteById(id);
	}
}
