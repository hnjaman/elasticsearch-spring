package com.hnj.code.offer.impl;

import com.hnj.code.offer.Offer;
import com.hnj.code.offer.OfferCondition;
import com.hnj.code.offer.OfferService;
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
