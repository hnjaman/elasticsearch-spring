package com.bol.test.assignment.product;

import com.bol.test.assignment.image.Image;
import java.util.List;

public interface ProductService {
    Product getProduct(Integer id);
    List<Image> getImagesByTitle(String title);
}
