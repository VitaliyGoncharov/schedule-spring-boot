package com.vitgon.schedule.service.database;

import java.util.List;

import com.vitgon.schedule.model.database.Group;
import com.vitgon.schedule.service.database.base.Service;

public interface GroupService extends Service<Group, Integer> {
	List<Group> findAllByMajorAndCourseNum(int majorId, int courseNum);
}
