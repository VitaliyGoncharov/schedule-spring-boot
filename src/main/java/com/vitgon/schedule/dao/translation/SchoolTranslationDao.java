package com.vitgon.schedule.dao.translation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.Locale;
import com.vitgon.schedule.model.database.School;
import com.vitgon.schedule.model.database.translation.SchoolTranslation;
import com.vitgon.schedule.model.database.translation.pk.SchoolTranslationId;

@Repository
public interface SchoolTranslationDao extends JpaRepository<SchoolTranslation, SchoolTranslationId> {
	SchoolTranslation findByTranslation(String translation);
	SchoolTranslation findByLocaleAndSchool(Locale locale, School school);
	
	@Query(value = "SELECT * from #{#entityName} WHERE locale_id = :localeId AND school_id = :schoolId",
			   nativeQuery = true)
	Optional<SchoolTranslation> findByLocaleIdAndSchoolId(
			@Param("localeId") Integer localeId,
			@Param("schoolId") Integer schoolId
	);
}
