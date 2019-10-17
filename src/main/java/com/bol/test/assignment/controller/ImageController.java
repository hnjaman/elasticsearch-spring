package com.bol.test.assignment.controller;

import com.bol.test.assignment.image.FilterCriteria;
import com.bol.test.assignment.image.Image;
import com.bol.test.assignment.image.ImageService;
import com.bol.test.assignment.image.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImageController {
    private ImageService imageService;
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/images")
    public List<Image> getImages(){
        SortCriteria sortCriteria = new SortCriteria();
        FilterCriteria filterCriteria = new FilterCriteria();
        return imageService.getImagesBy(sortCriteria, filterCriteria);
    }
}
