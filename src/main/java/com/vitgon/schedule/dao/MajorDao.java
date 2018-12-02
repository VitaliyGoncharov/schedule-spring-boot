package com.vitgon.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitgon.schedule.model.Major;

@Repository
public interface MajorDao extends JpaRepository<Major, Integer>{
}
