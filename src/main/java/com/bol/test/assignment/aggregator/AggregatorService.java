package com.bol.test.assignment.aggregator;

import com.bol.test.assignment.image.FilterCriteria;
import com.bol.test.assignment.image.ImageService;
import com.bol.test.assignment.image.SortCriteria;
import com.bol.test.assignment.offer.Offer;
import com.bol.test.assignment.offer.OfferService;
import com.bol.test.assignment.order.Order;
import com.bol.test.assignment.order.OrderService;
import com.bol.test.assignment.product.Product;
import com.bol.test.assignment.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.bol.test.assignment.offer.OfferCondition.AS_NEW;
import static com.bol.test.assignment.offer.OfferCondition.UNKNOWN;

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
            return new EnrichedOrder(order.getId(),-1, UNKNOWN, -1,
                    null, imageService.getImagesByTitle(null));
        Offer offer = offerService.getOffer(order.getOfferId());
        Product product = productService.getProduct(order.getProductId());
        return new EnrichedOrder(order.getId(),offer.getId(), offer.getCondition(), product.getId(),
                product.getTitle(), imageService.getImagesByTitle(product.getTitle()));
    }
}
