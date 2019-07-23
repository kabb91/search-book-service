package com.kabby.app.domain.repository;

import java.util.List;

import com.kabby.app.domain.model.History;

public interface HistoryRepositoryStore {
	
	/**
	 * 검색 시 로그인 된 유저에 대한 검색 히스토리 저장
	 * @param history
	 */
	public void createHistory(History history);
	
	/**
	 * 개인 히스토리에 대한 조회
	 * @param userId
	 * @return
	 */
	public List<History> retrieveMyHistoryByUserId(String userId);
	
	/**
	 * 검색 되어진 모든 히스토리를 조회
	 * @return
	 */
	public List<History> retrieveHistory();
	

}
