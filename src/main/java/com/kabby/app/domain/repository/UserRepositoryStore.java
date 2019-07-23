package com.kabby.app.domain.repository;

import com.kabby.app.domain.model.User;

public interface UserRepositoryStore {

	/**
	 * 유저 생성 함수
	 * @param user
	 */
	public void createUser(User user);
	
	/**
	 * 유저 생성시 유저의 아이디가 존재하는 체크
	 * @param userId
	 * @return
	 */
	public boolean checkExistUserId(String userId);
	
	/**
	 * 로그인시 유저의 패스워드를 비교
	 * @param userId
	 * @param userPassword
	 * @return
	 */
	public boolean checkPasswordByUserId(String userId, String userPassword);

	
	
}
