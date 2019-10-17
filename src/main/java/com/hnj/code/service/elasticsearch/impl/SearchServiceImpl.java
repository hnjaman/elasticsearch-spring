package com.hnj.code.service.elasticsearch.impl;

import com.hnj.code.model.elasticsearch.TestDocument;
import com.hnj.code.repository.elasticsearch.TestRepository;
import com.hnj.code.service.elasticsearch.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private TestRepository testRepository;

//	@Autowired
//	public SearchServiceImpl(TestRepository testRepository) {
//		this.testRepository = testRepository;
//	}

	@Override
	public TestDocument getMoviesByName(String name) {
		return testRepository.findByTitle(name);
	}
}
