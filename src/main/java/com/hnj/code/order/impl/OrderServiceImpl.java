package com.hnj.code.order.impl;

import com.hnj.code.order.Order;
import com.hnj.code.order.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	@Override
	public Order getOrder(Integer sellerId) {
		if(sellerId == null)
			throw new RuntimeException("Seller id can't be null");
		if(sellerId == 1000)
			return new Order(2, null, null);
		if(sellerId<0)
			return new Order(2, null, 4);
		if(sellerId==0)
			return new Order(2, 3, null);
		if(sellerId>99)
			return new Order(2, 3, -1);
		return new Order(2, 3, 4);
	}
}
