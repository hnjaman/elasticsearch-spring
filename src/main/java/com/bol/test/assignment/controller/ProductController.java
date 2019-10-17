package com.bol.test.assignment.controller;

import com.bol.test.assignment.image.Image;
import com.bol.test.assignment.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService){
		this.productService=productService;
	}

	@GetMapping("/products/{title}")
	public List<Image> imagesByProductTitle(@PathVariable("title") String title){
		return productService.getImagesByTitle(title);
	}
}
