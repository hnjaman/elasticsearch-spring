package com.bol.test.assignment.service.elasticsearch;

import com.bol.test.assignment.model.elasticsearch.TestDocument;

public interface TestESService {
	TestDocument getMoviesByName(String name);
}
