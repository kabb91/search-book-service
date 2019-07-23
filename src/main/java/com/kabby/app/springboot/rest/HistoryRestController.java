package com.kabby.app.springboot.rest;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kabby.app.domain.model.History;
import com.kabby.app.springboot.service.HistoryServiceImpl;

@RestController
@RequestMapping(value = "history")
public class HistoryRestController {

	Logger logger = LoggerFactory.getLogger(HistoryRestController.class);

	private HistoryServiceImpl historyServiceImpl;

	public HistoryRestController(HistoryServiceImpl historyServiceImpl) {
		this.historyServiceImpl = historyServiceImpl;
	}

	/**
	 * 검색 시 로그인 된 유저에 대한 검색 히스토리 저장
	 * @param history
	 */
	@PostMapping(value = { "", "/" })
	public void registerHistory(@RequestBody History history) {
		this.historyServiceImpl.registerHistory(history);
	}

	/**
	 * 개인 히스토리에 대한 조회
	 * @param userId
	 * @return 개인 히스토리에 대한 결과를 리스트 형태로 반환
	 */
	@GetMapping(value = { "/{userId}" })
	public List<History> findMyHistoryByUserId(@PathVariable(value = "userId") String userId) {

		return this.historyServiceImpl.findMyHistoryByUserId(userId);
	}

	/**
	 * 모든 검색 되어진 키워드에서 인기검색어 10개를 찾는 함수
	 * @return 인기검색어와 횟수를 리스트 형태로 반환
	 */
	@GetMapping(value = "/keyword/top10")
	public List<HashMap<String, Object>> findKeyWordTop10() {

		return this.historyServiceImpl.findKeyWordTop10();

	}

}
