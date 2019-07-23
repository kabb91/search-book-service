package com.kabby.app.springboot.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kabby.app.springboot.repository.jpo.HistoryJpo;


@Repository
public interface HistoryJpaRepository extends JpaRepository<HistoryJpo, String>{
	
	List<HistoryJpo> findByUserIdOrderBySearchDateDesc(String userId);

}
