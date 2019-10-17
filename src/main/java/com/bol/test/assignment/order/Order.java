package com.bol.test.assignment.order;

public class Order {
    private Integer id;
    private Integer offerId;
    private Integer productId;

    public Order(Integer id, Integer offerId, Integer productId) {
        this.id = id;
        this.offerId = offerId;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public Integer getProductId() {
        return productId;
    }
}
