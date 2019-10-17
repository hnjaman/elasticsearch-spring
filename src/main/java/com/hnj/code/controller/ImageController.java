package com.hnj.code.controller;

import com.hnj.code.image.FilterCriteria;
import com.hnj.code.image.Image;
import com.hnj.code.image.ImageService;
import com.hnj.code.image.SortCriteria;
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
