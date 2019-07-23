package com.kabby.app.springboot.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kabby.app.domain.repository.HistoryRepositoryStore;
import com.kabby.app.domain.service.logic.HistoryLogic;

@Service
@Transactional
public class HistoryServiceImpl extends HistoryLogic {

	public HistoryServiceImpl(HistoryRepositoryStore historyRepositoryStore) {
		super(historyRepositoryStore);
	}

	
}
