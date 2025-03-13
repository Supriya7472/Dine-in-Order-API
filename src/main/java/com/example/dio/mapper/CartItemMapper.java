package com.example.dio.mapper;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemResponse mapToCartItem(CartItem cartItem);
}
