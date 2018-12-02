package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.translation.SubjectTranslation;
import com.vitgon.schedule.model.translation.pk.SubjectTranslationId;

@Repository
public interface SubjectTranslationDao extends JpaRepository<SubjectTranslation, SubjectTranslationId> {
	SubjectTranslation findByTitle(String title);
}
