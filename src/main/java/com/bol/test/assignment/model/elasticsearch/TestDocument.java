package com.bol.test.assignment.model.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Document(indexName = "test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDocument {
	@Id
	private Integer id;
	@Field(type = FieldType.Text)
	private String director;
	@Field(type = FieldType.Nested)
	private List<String> genre;
	@Field(type = FieldType.Integer)
	private Integer year;
	@Field(type = FieldType.Nested)
	private List<String> actor;
	@Field(type = FieldType.Text)
	private String title;
}
