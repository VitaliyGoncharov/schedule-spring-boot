package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.database.translation.MajorTranslation;
import com.vitgon.schedule.model.database.translation.pk.MajorTranslationId;

@Repository
public interface MajorTranslationDao extends JpaRepository<MajorTranslation, MajorTranslationId> {
	MajorTranslation findByTitle(String title);
}
