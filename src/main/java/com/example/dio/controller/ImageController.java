package com.example.dio.controller;

import com.example.dio.model.Image;
import com.example.dio.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class ImageController {
    private ImageService imageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("images")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("foodItemId") Long foodItemId){
        try {
            String imageUrl = imageService.uploadImage(file, foodItemId);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading image: " + e.getMessage());
        }

    }

}
