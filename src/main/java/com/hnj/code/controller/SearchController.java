package com.hnj.code.controller;

import com.hnj.code.model.elasticsearch.TestDocument;
import com.hnj.code.service.elasticsearch.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search/movies")
    public TestDocument searchMovie(@RequestParam String name){
        return searchService.getMoviesByName(name);
    }
}
