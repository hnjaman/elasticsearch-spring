package com.bol.test.assignment.service.elasticsearch.impl;

import com.liquid.common.model.elasticsearch.TestDocument;
import com.liquid.common.repository.elasticsearch.TestRepository;
import com.liquid.common.service.elasticsearch.TestESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestESServiceImpl implements TestESService {
	@Autowired
	private TestRepository testRepository;

//	@Autowired
//	public TestESServiceImpl(TestRepository testRepository) {
//		this.testRepository = testRepository;
//	}

	@Override
	public TestDocument getMoviesByName(String name) {
		return testRepository.findByTitle(name);
	}
}
