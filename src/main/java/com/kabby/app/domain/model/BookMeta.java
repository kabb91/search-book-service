package com.kabby.app.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookMeta {
	
	private boolean isEnd;     //현재 페이지가 마지막 페이지인지 여부. 값이 false이면 page를 증가시켜 다음 페이지를 요청할 수 있음
	private int pageableCount; //검색결과로 제공 가능한 문서수
	private int totalCount;    //전체 검색된 문서수
	private int endPage;       //마지막 페이지의 숫자

}
