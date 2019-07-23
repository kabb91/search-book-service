package com.kabby.app.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {
		
	private String title;            //제목
	private String thumbnail;        //썸네
	private String contents;         //소개
	private String isbn;             //ISBN
	private String publisher;        //출판사 
	private String[] authors;        //저자
	private String publicationDate;  //출판일
	private int price;               //정가
	private int salePrice;           //판매가
	

}
