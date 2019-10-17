package com.bol.test.assignment.repository.elasticsearch;

import com.liquid.common.model.elasticsearch.TestDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ElasticsearchRepository<TestDocument, String> {
	TestDocument findByTitle(String name);
}
