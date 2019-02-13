package com.vitgon.schedule.service.database.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vitgon.schedule.dao.MajorDao;
import com.vitgon.schedule.dao.translation.MajorTranslationDao;
import com.vitgon.schedule.model.database.Major;
import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.service.database.MajorService;

@Service
@Transactional
public class MajorServiceImpl implements MajorService {

	@Autowired
	private MajorDao majorDao;
	
	@Autowired
	private MajorTranslationDao majorTranslDao;
	
	public MajorServiceImpl() {
	}

	@Override
	public Major save(Major obj) {
		return majorDao.save(obj);
	}

	@Override
	public Major update(Major obj) {
		return majorDao.save(obj);
	}

	@Override
	public Major findById(Integer id) {
		return majorDao.findById(id).orElse(null);
	}

	@Override
	public List<Major> findAll() {
		return majorDao.findAll();
	}

	@Override
	public Major findByTitle(String title) {
		MajorTranslation majorTransl = majorTranslDao.findByTitle(title);
		return majorTransl.getMajor();
	}
	
	@Override
	public Major findByUrl(String url) {
		return majorDao.findByUrl(url);
	}
}
