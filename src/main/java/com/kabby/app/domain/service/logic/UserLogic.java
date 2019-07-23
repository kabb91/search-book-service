package com.kabby.app.domain.service.logic;

import java.util.ArrayList;

import com.kabby.app.domain.model.User;
import com.kabby.app.domain.repository.UserRepositoryStore;
import com.kabby.app.domain.service.spec.UserService;

public class UserLogic implements UserService {
	
	private UserRepositoryStore userRepositoryStore;
	
	public UserLogic(UserRepositoryStore userRepositoryStore) {
		this.userRepositoryStore=userRepositoryStore;
	}

	@Override
	public void registerUser(User user) {
		
		user.setHistories(new ArrayList<>());
		userRepositoryStore.createUser(user);
	}

	@Override
	public boolean checkExistUserId(String userId) {

		
		return userRepositoryStore.checkExistUserId(userId);
	}

	@Override
	public boolean checkPasswordByUserId(String userId, String userPassword) {

		return userRepositoryStore.checkPasswordByUserId(userId,userPassword);
	}
	
	
	

}
