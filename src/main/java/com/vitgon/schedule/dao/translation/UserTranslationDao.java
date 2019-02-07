package com.vitgon.schedule.dao.translation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.auth.User;
import com.vitgon.schedule.model.translation.UserTranslation;
import com.vitgon.schedule.model.translation.pk.UserTranslationId;

@Repository
public interface UserTranslationDao extends JpaRepository<UserTranslation, UserTranslationId> {
	UserTranslation findByUserTranslationIdLocaleAndUserTranslationIdUser(Locale locale, User user);
}
