package com.kabby.app.springboot.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kabby.app.springboot.listen.api.SearchBookService;

@RestController
@RequestMapping(value = "search")
@CrossOrigin("*")
public class BookSearchRestController {

	Logger logger = LoggerFactory.getLogger(BookSearchRestController.class);

	final SearchBookService searchBookService;

	public BookSearchRestController(SearchBookService searchBookService) {
		this.searchBookService = searchBookService;
	}

	@GetMapping("/book")
	public Object findBooks(@RequestParam(value = "target", required = false) String target,
						    @RequestParam(value = "query") String query, 
						    @RequestParam(value = "size", required = false) String size,
						    @RequestParam(value = "page", required = false) String page) {

		
		return searchBookService.findBooks(target, query, size, page).toString();
		

	}

}
