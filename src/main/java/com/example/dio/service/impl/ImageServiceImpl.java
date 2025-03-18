package com.example.dio.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.example.dio.model.FoodItem;
import com.example.dio.model.Image;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.ImageRepository;
import com.example.dio.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final Cloudinary cloudinary;
    private final FoodItemRepository foodItemRepository;
    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile file,Long foodItemId) throws IOException {
        FoodItem foodItem=foodItemRepository.findById(foodItemId)
                .orElseThrow(()-> new RuntimeException("Food item not found with id: "+foodItemId));

        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        Image image=new Image();
        String imageUrl=result.get("url").toString();
        image.setFoodItem(foodItem);
        image.setImageUrl(imageUrl);
        imageRepository.save(image);

        return imageUrl;
    }

}
