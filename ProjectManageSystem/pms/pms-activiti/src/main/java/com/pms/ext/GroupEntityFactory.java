package com.pms.ext;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupEntityFactory implements SessionFactory {

	@Autowired
	GroupEntityManager groupEntityManager;

	@Override
	public Class<?> getSessionType() {
		return GroupIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return groupEntityManager;
	}
}
