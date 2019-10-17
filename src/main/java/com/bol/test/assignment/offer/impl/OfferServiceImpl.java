package com.bol.test.assignment.offer.impl;

import com.bol.test.assignment.offer.Offer;
import com.bol.test.assignment.offer.OfferCondition;
import com.bol.test.assignment.offer.OfferService;
import com.bol.test.assignment.order.Order;
import com.bol.test.assignment.order.OrderService;
import com.bol.test.assignment.order.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
	@Override
	public Offer getOffer(Integer id) {
		if(id == null)
			throw new RuntimeException("Offerservice failed due to offer id is null");
		return new Offer(id, OfferCondition.AS_NEW);
	}
}
