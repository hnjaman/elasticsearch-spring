package com.hnj.code;

import com.hnj.code.image.Image;
import com.hnj.code.offer.OfferCondition;

import java.util.Collection;

public class EnrichedOrder {
    private int id;
    private int offerId;
    private OfferCondition offerCondition;
    private int productId;
    private String productTitle;
    private Collection<Image> images;

    public EnrichedOrder(int id, int offerId, OfferCondition offerCondition, int productId, String productTitle, Collection<Image> images) {
        this.id = id;
        this.offerId = offerId;
        this.offerCondition = offerCondition;
        this.productId = productId;
        this.productTitle = productTitle;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public int getOfferId() {
        return offerId;
    }

    public OfferCondition getOfferCondition() {
        return offerCondition;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public Collection<Image> getImages() {
        return images;
    }
}
