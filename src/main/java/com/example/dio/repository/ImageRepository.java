package com.example.dio.repository;

import com.example.dio.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {

    List<Image> findByFoodItem_ItemId(Long itemId);
}
