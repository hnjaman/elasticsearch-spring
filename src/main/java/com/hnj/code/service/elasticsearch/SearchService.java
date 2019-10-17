package com.hnj.code.service.elasticsearch;

import com.hnj.code.model.elasticsearch.TestDocument;

public interface SearchService {
	TestDocument getMoviesByName(String name);
}
