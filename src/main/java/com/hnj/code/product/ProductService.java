package com.hnj.code.product;

import com.hnj.code.image.Image;
import java.util.List;

public interface ProductService {
    Product getProduct(Integer id);
    List<Image> getImagesByTitle(String title);
}
