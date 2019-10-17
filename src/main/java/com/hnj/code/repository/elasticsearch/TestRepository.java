package com.hnj.code.repository.elasticsearch;

import com.hnj.code.model.elasticsearch.TestDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ElasticsearchRepository<TestDocument, String> {
	TestDocument findByTitle(String name);
}
