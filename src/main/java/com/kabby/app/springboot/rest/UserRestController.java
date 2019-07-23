package com.kabby.app.springboot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kabby.app.domain.model.User;
import com.kabby.app.springboot.service.UserServiceImpl;

@RestController
@RequestMapping(value = "user")
public class UserRestController {
	
	Logger logger = LoggerFactory.getLogger(UserRestController.class);

	private UserServiceImpl userServiceImpl;

	public UserRestController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	/**
	 * 회원 가입시 생성하려고 하는 아이디가 이미 존재하는 여부를 체크 하는 API <br>
	 * false : 아이디 생성 가능 <br>
	 * true  : 이미 아이디가 존재 함으로 생성 불가능  
	 * 
	 * @param userId
	 * @return boolean
	 */
	@GetMapping(value="/{userId}/check")
	public boolean checkExistUserId(@PathVariable(value="userId") String userId) {
		
		return this.userServiceImpl.checkExistUserId(userId);
	}
	
	/**
	 * 로그인 시 아이디와 패스워드 체크 하는 API <br>
	 * true  : 로그인 성공 <br>
	 * false : 로그인 실패 
	 * 
	 * @param userId
	 * @param userPassword
	 * @return boolean 
	 */
	@GetMapping(value="/{userId}/{userPassword}/check")
	public boolean checkPasswordByUserId(@PathVariable(value="userId") String userId, @PathVariable(value="userPassword") String userPassword) {
				
		return this.userServiceImpl.checkPasswordByUserId(userId,userPassword);
	}
	
	
	/**
	 * 회원 가입 시 회원 정보를 웹으로 부터 받아와서 저장하는 API
	 * 
	 * @param user
	 */
	@PostMapping(value = { "", "/" })
	public void registerUser(@RequestBody User user) {
		
		this.userServiceImpl.registerUser(user);
	}

}
