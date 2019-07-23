package com.kabby.app.springboot.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kabby.app.domain.repository.UserRepositoryStore;
import com.kabby.app.domain.service.logic.UserLogic;

@Service
@Transactional
public class UserServiceImpl extends UserLogic {
	
	public UserServiceImpl(UserRepositoryStore userRepositoryStore) {
		super(userRepositoryStore);
	}
}
