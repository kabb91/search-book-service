package com.kabby.app.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History {

	private String historyId;          //히스토리에 대한 고유 
	private String query;              //검색한 키워드 
	private LocalDateTime searchDate;  //검색한 날짜
	 
	private String userId;             //검색한 유저의 아이디
	


}
