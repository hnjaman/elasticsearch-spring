package com.hnj.code;

import com.bol.test.assignment.image.*;
import com.hnj.code.image.impl.ImageServiceImpl;
import com.hnj.code.offer.OfferService;
import com.hnj.code.offer.impl.OfferServiceImpl;
import com.hnj.code.order.OrderService;
import com.hnj.code.order.impl.OrderServiceImpl;
import com.hnj.code.product.ProductService;
import com.hnj.code.product.impl.ProductServiceImpl;
import com.hnj.code.image.Format;
import com.hnj.code.image.Image;
import com.hnj.code.image.ImageService;
import com.hnj.code.offer.OfferCondition;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class AggregatorServiceTest {
    OrderService orderService = new OrderServiceImpl();
    OfferService offerService = new OfferServiceImpl();
    ImageService imageService = new ImageServiceImpl();
    ProductService productService = new ProductServiceImpl(imageService);

//    private final Integer sellerId = 1;
//    private final Integer orderId = 2;
//    private final Integer offerId = 3;
//    private final Integer productId = 4;
//    private String title = "Title";
//    private Image image = new Image(title, Format.GIF, 1L);
//    private Image defaultImage = new Image("Default Image", Format.GIF, 123L);

    AggregatorService aggregatorService = new AggregatorService(orderService, offerService,
                productService, imageService);

    @Test
    public void simpleHappyFlow() {
//        when(orderService.getOrder(sellerId)).thenReturn(new Order(orderId, offerId, productId));
//        when(offerService.getOffer(offerId)).thenReturn(new Offer(offerId, AS_NEW));
//        when(productService.getProduct(productId)).thenReturn(new Product(productId, title));
//        when(imageService.getImagesBy(any(SortCriteria.class), any(FilterCriteria.class) )).thenReturn(Collections.singletonList(image));

        Integer sellerId = 1;
        Integer orderId = 2;
        String title = "dfff072e-11fd-40ca-8e13-c8406daef735";
        Image image = new Image(title, Format.JPG, 18728L);
        EnrichedOrder enrichedOrder = aggregatorService.enrich(sellerId);
        assertEquals(image.getName(), enrichedOrder.getImages().stream().findFirst().get().getName());
        assertThat(enrichedOrder.getId(), is(orderId));
    }

    @Test(timeout = 3500)
    public void offerAndProductServicesAreSlow() {
//        when(orderService.getOrder(sellerId)).thenReturn(new Order(orderId, offerId, productId));
//        when(offerService.getOffer(offerId)).thenAnswer(
//                (InvocationOnMock invocationOnMock) -> {
//                    Thread.sleep(1500);
//                    return new Offer(offerId, AS_NEW);
//                }
//        );
//        when(productService.getProduct(productId)).thenAnswer(
//                (InvocationOnMock invocationOnMock) -> {
//                    Thread.sleep(1500);
//                    return new Product(productId, title);
//                }
//        );
//        when(imageService.getImagesBy(any(SortCriteria.class), any(FilterCriteria.class))).thenAnswer(
//                (InvocationOnMock invocationOnMock) -> {
//                    Thread.sleep(1500);
//                    return Collections.singletonList(image);
//                }
//        );

        Integer sellerId = 1;
        Integer orderId = 2;
        String title = "dfff072e-11fd-40ca-8e13-c8406daef735";
        Image image = new Image(title, Format.JPG, 18728L);
        EnrichedOrder enrichedOrder = aggregatorService.enrich(sellerId);
        assertThat(enrichedOrder.getId(), is(orderId));
        assertThat(enrichedOrder.getOfferCondition(), CoreMatchers.is(OfferCondition.AS_NEW));
        assertThat(enrichedOrder.getProductTitle(), is(title));
        assertEquals(image.getName(), enrichedOrder.getImages().stream().findFirst().get().getName());
    }

    @Test(expected = RuntimeException.class)
    public void offerServiceFailed() {
//        when(orderService.getOrder(sellerId)).thenReturn(new Order(orderId, offerId, productId));
//        when(offerService.getOffer(offerId)).thenThrow(new RuntimeException("Offer Service failed"));
//        when(productService.getProduct(productId)).thenReturn(new Product(productId, title));

        Integer sellerId = -1;
        Integer orderId = 1;
        String title = "dfff072e-11fd-40ca-8e13-c8406daef735";
        Image image = new Image(title, Format.JPG, 18728L);
        EnrichedOrder enrichedOrder = aggregatorService.enrich(sellerId);
        assertThat(enrichedOrder.getId(), is(orderId));
        assertThat(enrichedOrder.getProductTitle(), is(title));
        assertThat(enrichedOrder.getOfferId(), is(-1));
        assertThat(enrichedOrder.getOfferCondition(), CoreMatchers.is(OfferCondition.UNKNOWN));
    }

    @Test(expected = RuntimeException.class)
    public void productServiceFailed() {
//        when(orderService.getOrder(sellerId)).thenReturn(new Order(orderId, offerId, productId));
//        when(offerService.getOffer(offerId)).thenReturn(new Offer(offerId, AS_NEW));
//        when(productService.getProduct(productId)).thenThrow(new RuntimeException("Product Service failed"));

        Integer sellerId = 0;
        Integer orderId = 1;
        Integer offerId = 3;
        String title = "dfff072e-11fd-40ca-8e13-c8406daef735";
        Image image = new Image(title, Format.JPG, 18728L);
        EnrichedOrder enrichedOrder = aggregatorService.enrich(sellerId);
        assertThat(enrichedOrder.getId(), is(orderId));
        assertThat(enrichedOrder.getProductId(), is(-1));
        assertNull(enrichedOrder.getProductTitle());
        assertThat(enrichedOrder.getOfferId(), is(offerId));
        assertThat(enrichedOrder.getOfferCondition(), CoreMatchers.is(OfferCondition.AS_NEW));
    }

    @Test(expected = RuntimeException.class)
    public void productServiceAndOfferServiceFailed() {
//        when(orderService.getOrder(sellerId)).thenReturn(new Order(orderId, offerId, productId));
//        when(offerService.getOffer(offerId)).thenThrow(new RuntimeException("Offer Service failed"));
//        when(productService.getProduct(productId)).thenThrow(new RuntimeException("Product Service failed"));

        Integer sellerId = 0;
        Integer orderId = 1;
        Integer offerId = 3;
        String title = "dfff072e-11fd-40ca-8e13-c8406daef735";
        Image image = new Image(title, Format.JPG, 18728L);
        EnrichedOrder enrichedOrder = aggregatorService.enrich(sellerId);
        assertThat(enrichedOrder.getId(), is(orderId));
        assertThat(enrichedOrder.getOfferId(), is(-1));
        assertThat(enrichedOrder.getOfferCondition(), CoreMatchers.is(OfferCondition.UNKNOWN));
        assertThat(enrichedOrder.getProductId(), is(-1));
        assertNull(enrichedOrder.getProductTitle());
    }

    @Test(expected = RuntimeException.class)
    public void orderServiceFailed() {
        //when(orderService.getOrder(sellerId)).thenThrow(new RuntimeException("Order service failed"));
        aggregatorService.enrich(null);
    }

    @Test
    public void productServiceAndOfferServiceNulls() {
//        when(orderService.getOrder(sellerId)).thenReturn(new Order(orderId, offerId, productId));
//        when(offerService.getOffer(offerId)).thenReturn(null);
//        when(productService.getProduct(productId)).thenReturn(null);

        Integer sellerId = 1000;
        Integer orderId = 2;
        EnrichedOrder enrichedOrder = aggregatorService.enrich(sellerId);
        assertThat(enrichedOrder.getId(), is(orderId));
        assertThat(enrichedOrder.getOfferId(), is(-1));
        assertThat(enrichedOrder.getOfferCondition(), CoreMatchers.is(OfferCondition.UNKNOWN));
        assertThat(enrichedOrder.getProductId(), is(-1));
        assertNull(enrichedOrder.getProductTitle());
    }

    @Test
    public void imageServiceFailed() {
//        when(orderService.getOrder(sellerId)).thenReturn(new Order(orderId, offerId, productId));
//        when(offerService.getOffer(offerId)).thenReturn(new Offer(offerId, AS_NEW));
//        when(productService.getProduct(productId)).thenReturn(new Product(productId, title));
//        when(imageService.getImagesBy(any(SortCriteria.class), any(FilterCriteria.class))).thenThrow(new RuntimeException());

        Integer sellerId = 100;
        Integer orderId = 2;
        Integer offerId = 3;
        String title = "Default Image";
        Image defaultImage = new Image("Default Image", Format.GIF, 123L);
        EnrichedOrder enrichedOrder = aggregatorService.enrich(sellerId);
        assertThat(enrichedOrder.getId(), is(orderId));
        assertThat(enrichedOrder.getOfferId(), is(offerId));
        assertThat(enrichedOrder.getProductTitle(), is(title));
        assertThat(enrichedOrder.getOfferCondition(), CoreMatchers.is(OfferCondition.AS_NEW));
        //assertThat(defaultImage, new ReflectionEquals(enrichedOrder.getImages().stream().findFirst().get(), null));
        assertThat(defaultImage.getName(), is(enrichedOrder.getImages().stream().findFirst().get().getName()));
    }
}
