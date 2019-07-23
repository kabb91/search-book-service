package com.kabby.app.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.kabby.app.domain.model.User;
import com.kabby.app.springboot.repository.jpa.UserJpaRepository;
import com.kabby.app.springboot.repository.jpo.UserJpo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserServiceTest {

	@Autowired
    MockMvc mockMvc;	
	
	
	@Autowired
	UserJpaRepository userJpaRepository;
	
		
	
	@Test
	public void checkExistUserId() throws Exception {
		mockMvc.perform(get("/user/user2/check"))
        .andExpect(status().isOk())
        .andExpect(content().string("false"))
        .andDo(print());
		
	}
	
	@Test
    public void registerUser() {
        User user = new User();
        user.setUserId("user1");
        user.setUserPassword("qlalfqjsgh");
        user.setUserName("홍길동");
        
        UserJpo userJpo = new UserJpo(user);     
        
        userJpaRepository.save(userJpo);
    }
	
	
	@Test
	public void checkPasswordByUserId() throws Exception {
		mockMvc.perform(get("/user/user1/qlalfqjsgh/check"))
        .andExpect(status().isOk())
        .andExpect(content().string("false"))
        .andDo(print());
		
	}
	

	
	
			
	

}
