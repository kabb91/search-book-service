package com.kabby.app.springboot.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kabby.app.springboot.repository.jpo.UserJpo;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpo, String>{

	
}
