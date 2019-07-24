package com.kabby.app.springboot.listen.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kabby.app.domain.model.Book;
import com.kabby.app.domain.model.BookMeta;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SearchBookService {

	@Autowired
	KakaoSearchBookApiHelper kakaoSearchBookApiHelper;

	@Autowired
	NaverSearchBookApiHelper naverSearchBookApiHelper;
	
	static final int ABNORMAL_SALE_PRICE=0;

	/**
	 * 
	 * Kakao API을 통해서 책 검색 한다.
	 * 
	 * 만약에 Kakao API에 장애가 생겼을 시에는 fallbackMethod {@link #findBooksFallback(String, String, String, String)} 을 이용해서 Naver API로 대체한다.
	 * 
	 * @param targe
	 * @param query
	 * @param size
	 * @param page
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "findBooksFallback")
	public Object findBooks(String targe, String query, String size, String page) {

		Map<String, String> params = new HashMap<String, String>();

		if (!targe.equals("total")) {
			params.put("target", targe);
		}

		params.put("query", query);
		params.put("size", size);
		params.put("page", page);

		List<Book> bookList = new ArrayList<Book>();

		String kakaoApiConvertString = kakaoSearchBookApiHelper.getMessageToMe(params).toString();
		JSONObject kakaoApiJson = new JSONObject(kakaoApiConvertString);

		JSONArray documents = kakaoApiJson.getJSONArray("documents");

		for (int i = 0; i < documents.length(); i++) {
			Book book = new Book();
			JSONObject document = documents.getJSONObject(i);
			book.setTitle(document.getString("title"));
			book.setThumbnail(document.getString("thumbnail"));
			book.setContents(document.getString("contents"));
			book.setIsbn(document.getString("isbn"));
			book.setPublisher(document.getString("publisher"));
			book.setPublicationDate(document.getString("datetime"));
			book.setPrice(document.getInt("price"));
			
			
			if(document.getInt("sale_price")== -1) {
				int abnormalitySalePrice = ABNORMAL_SALE_PRICE;
				book.setSalePrice(abnormalitySalePrice);
			}else {
				book.setSalePrice(document.getInt("sale_price"));
			}
			
			JSONArray authorsArr = document.getJSONArray("authors");

			String[] authors = new String[authorsArr.length()];
			for (int j = 0; j < authors.length; j++) {
				authors[j] = authorsArr.optString(j);
			}

			book.setAuthors(authors);
			bookList.add(book);
		}

		JSONObject meta = (JSONObject) kakaoApiJson.get("meta");
		BookMeta bookMeta = new BookMeta();
		bookMeta.setTotalCount(meta.getInt("total_count"));
		bookMeta.setEnd(meta.getBoolean("is_end"));
		bookMeta.setPageableCount(meta.getInt("pageable_count"));

				
		JSONObject result = new JSONObject();
		result.put("meta", meta);
		result.put("books", bookList);

		return result;

	}

	
	/**
	 * 
	 * Naver API을 통해서 책 검색 한다.
	 * 
	 * 책 기본 검색일때와 상세 검색일때 각각 다르게 API를 호출(XML형태로 읽어서 JSON으로 변환)해서 결과를 가져온다. 
	 * 
	 * 
	 * @param targe
	 * @param query
	 * @param size
	 * @param page
	 * @return
	 */
	public Object findBooksFallback(String targe, String query, String size, String page) {

		Map<String, String> params = new HashMap<String, String>();
		
		if (!targe.equals("total")) {
			if(targe.equals("title")) {
				params.put("d_titl", query);
				
			}else if(targe.equals("isbn")) {
				params.put("d_isbn", query);
				
			}else if(targe.equals("publisher")) {
				params.put("d_publ", query);
				
			}else if(targe.equals("person")) {
				params.put("d_auth", query);
			}
			
			
		}else {
			params.put("query", query);
		}
		
		params.put("display", size);
		params.put("start", page);

		List<Book> bookList = new ArrayList<Book>();
		String naverApiConvertString = null;
		
		
		if(!targe.equals("total")) {
			
			naverApiConvertString = naverSearchBookApiHelper.getMessageToMeDetail(params).toString();
		
		}else{
			
			naverApiConvertString = naverSearchBookApiHelper.getMessageToMeBasic(params).toString();
		}		
		

		
		JSONObject xmlJSONObj = XML.toJSONObject(naverApiConvertString);
		JSONObject rss = xmlJSONObj.getJSONObject("rss");
		JSONObject channel = rss.getJSONObject("channel");
		
		try {
			JSONArray documents = channel.getJSONArray("item");
			
			for (int i = 0; i < documents.length(); i++) {
				Book book = new Book();
				JSONObject document = documents.getJSONObject(i);
				
				String scReplaceTitle = document.getString("title").replaceAll("<b>", "").replaceAll("</b>", "");
				book.setTitle(scReplaceTitle);
				
				book.setThumbnail(document.getString("image"));		
				
				String scReplaceDescription = document.getString("description").replaceAll("<b>", "").replaceAll("</b>", "");
				book.setContents(scReplaceDescription);
								
				book.setIsbn(document.get("isbn").toString());
				
				String scReplacePublisher = document.getString("publisher").replaceAll("<b>", "").replaceAll("</b>", "");
				book.setPublisher(scReplacePublisher);
				
				book.setPublicationDate(Integer.toString(document.getInt("pubdate")));
				
				book.setPrice(document.getInt("price"));
				
				try {
					book.setSalePrice(Integer.parseInt(document.get("discount").toString()));
				}catch(NumberFormatException e) {
					if(document.get("discount").toString().equals("")) {
						book.setSalePrice(0);
					}
				}catch (Exception e) {
					e.getMessage();
				}
				String[] authors = { document.getString("author").replaceAll("<b>", "").replaceAll("</b>", "").replaceAll("\"", "").replaceAll("\\|", " , ") };

				book.setAuthors(authors);
				bookList.add(book);
			}
				
		}catch(JSONException e) {
			
		}catch (Exception e) {
			e.getMessage();
		}
		
	
		
		
			
		 
			
		JSONObject meta = new JSONObject();
		meta.put("total_count", channel.get("total"));
		JSONObject result = new JSONObject();
		result.put("books", bookList);
		
		result.put("meta", meta);
		return result;

	}
}