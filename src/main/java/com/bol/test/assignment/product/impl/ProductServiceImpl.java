package com.bol.test.assignment.product.impl;

import com.bol.test.assignment.image.Image;
import com.bol.test.assignment.image.ImageService;
import com.bol.test.assignment.image.impl.ImageServiceImpl;
import com.bol.test.assignment.order.Order;
import com.bol.test.assignment.order.OrderService;
import com.bol.test.assignment.product.Product;
import com.bol.test.assignment.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	private ImageService imageService;
	@Autowired
	public ProductServiceImpl(ImageService imageService){
		this.imageService=imageService;
	}

	@Override
	public Product getProduct(Integer id) {
		if(id == null)
			throw new RuntimeException("Product service failed due to null product id");
		if(id<0)
			return new Product(id, "Default Image");
		return new Product(id, "dfff072e-11fd-40ca-8e13-c8406daef735");
	}

	@Override
	public List<Image> getImagesByTitle(String title) {
		return imageService.getImagesByTitle(title);
	}
}
