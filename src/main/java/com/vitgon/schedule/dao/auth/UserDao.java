package com.vitgon.schedule.dao.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.auth.User;

@Repository("userRepository")
public interface UserDao extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
