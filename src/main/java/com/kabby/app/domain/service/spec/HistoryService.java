package com.kabby.app.domain.service.spec;

import java.util.HashMap;
import java.util.List;

import com.kabby.app.domain.model.History;

public interface HistoryService {
	
	/**
	 * 검색 시 로그인 된 유저에 대한 검색 히스토리 저장
	 * @param history
	 */
	public void registerHistory(History history);
	
	/**
	 * 개인 히스토리에 대한 조회
	 * @param userId
	 * @return
	 */
	public List<History> findMyHistoryByUserId(String userId);
	
	/**
	 * 모든 검색 되어진 키워드에서 인기검색어 10개를 찾는 함수
	 * @return
	 */
	public List<HashMap<String, Object>> findKeyWordTop10();
}
