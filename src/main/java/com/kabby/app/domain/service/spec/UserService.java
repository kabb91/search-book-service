package com.kabby.app.domain.service.spec;

import com.kabby.app.domain.model.User;

public interface UserService {
	public void registerUser(User user);
	public boolean checkExistUserId(String userId);
	public boolean checkPasswordByUserId(String userId,String userPassword);

	
}
