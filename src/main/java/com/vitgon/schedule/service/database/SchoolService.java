package com.vitgon.schedule.service.database;

import java.util.List;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.projection.SchoolProjection;
import com.vitgon.schedule.service.database.base.Service;

public interface SchoolService extends Service<School, Integer> {
	School findByTranslation(String translation);
	List<School> findAllByLocale(Locale locale);
	School findByName(String name);
	List<SchoolProjection> getAllJoiningWithMajors();
	List<SchoolProjection> getAllJoiningWithMajors(Integer localeId);
}
