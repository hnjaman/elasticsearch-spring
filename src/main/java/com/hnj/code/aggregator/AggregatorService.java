package com.hnj.code.aggregator;

import com.hnj.code.image.ImageService;
import com.hnj.code.offer.Offer;
import com.hnj.code.offer.OfferService;
import com.hnj.code.order.Order;
import com.hnj.code.order.OrderService;
import com.hnj.code.product.Product;
import com.hnj.code.product.ProductService;
import com.hnj.code.offer.OfferCondition;
import org.springframework.stereotype.Service;

@Service
public class AggregatorService {
    private OrderService orderService;
    private OfferService offerService;
    private ProductService productService;
    private ImageService imageService;

    public AggregatorService(OrderService orderService,
                             OfferService offerService,
                             ProductService productService,
                             ImageService imageService) {
        this.orderService = orderService;
        this.offerService = offerService;
        this.productService = productService;
        this.imageService = imageService;
    }

    public EnrichedOrder enrich(Integer sellerId) {
        Order order = orderService.getOrder(sellerId);
        if(order.getOfferId() == null && order.getProductId() == null)
            return new EnrichedOrder(order.getId(),-1, OfferCondition.UNKNOWN, -1,
                    null, imageService.getImagesByTitle(null));
        Offer offer = offerService.getOffer(order.getOfferId());
        Product product = productService.getProduct(order.getProductId());
        return new EnrichedOrder(order.getId(),offer.getId(), offer.getCondition(), product.getId(),
                product.getTitle(), imageService.getImagesByTitle(product.getTitle()));
    }
}
