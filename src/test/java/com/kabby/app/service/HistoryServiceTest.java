package com.kabby.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.kabby.app.domain.model.History;
import com.kabby.app.domain.model.User;
import com.kabby.app.springboot.repository.jpa.HistoryJpaRepository;
import com.kabby.app.springboot.repository.jpa.UserJpaRepository;
import com.kabby.app.springboot.repository.jpo.HistoryJpo;
import com.kabby.app.springboot.repository.jpo.UserJpo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class HistoryServiceTest {
	@Autowired
    MockMvc mockMvc;	
	
	
	@Autowired
	HistoryJpaRepository historyJpaRepository;
	@Autowired
	UserJpaRepository userJpaRepository;
	
	@Before
	public void registerUser() {
        User user = new User();
        user.setUserId("user1");
        user.setUserPassword("qlalfqjsgh");
        user.setUserName("홍길동");
        
        UserJpo userJpo = new UserJpo(user);     
        
        userJpaRepository.save(userJpo);
        
        
	}
	
	@Test
    public void registerHistory() {
        History history = new History();
        history.setQuery("스프링");
        history.setUserId("user1");
        
        HistoryJpo historyJpo = new HistoryJpo(history);
        historyJpaRepository.save(historyJpo);
    }
	
	@Test
    public void findMyHistoryByUserId() throws Exception {
        History history = new History();
        history.setQuery("스프링");
        history.setUserId("user1");        
        history.setSearchDate(LocalDateTime.now());
        HistoryJpo historyJpo = new HistoryJpo(history);
        historyJpaRepository.save(historyJpo);
        
        Optional<HistoryJpo> historyJpoTest = historyJpaRepository.findById(historyJpo.getHistoryId());       
        assertThat(historyJpoTest.get().toModel().getUserId()).isEqualTo("user1");
        assertThat(historyJpoTest.get().toModel().getQuery()).isEqualTo("스프링");

	
    }
	
}
