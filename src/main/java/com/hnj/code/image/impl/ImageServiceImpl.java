package com.hnj.code.image.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hnj.code.image.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public List<Image> getImagesBy(SortCriteria sortCriteria, FilterCriteria filterCriteria) {
        List<Image> images = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Image>> typeReference = new TypeReference<List<Image>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/generated.json");
        try {
            images = mapper.readValue(inputStream,typeReference);
        } catch (IOException e){
            System.out.println("Unable to get images: " + e.getMessage());
        }
        return images;
    }

    @Override
    public List<Image> getImagesByTitle(String title){
        SortCriteria sortCriteria = new SortCriteria();
        FilterCriteria filterCriteria = new FilterCriteria();
        List<Image> images = getImagesBy(sortCriteria, filterCriteria).stream()
                .filter( image -> image.getName().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        if(images.isEmpty())
            images.add(new Image("Default Image", Format.GIF, 123L));
            //throw new RuntimeException("Image service failed due to no image found with this title");
        return images;
    }
}
