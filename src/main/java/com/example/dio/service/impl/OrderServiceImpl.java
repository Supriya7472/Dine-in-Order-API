package com.example.dio.service.impl;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.mapper.CartItemMapper;
import com.example.dio.mapper.OrderMapper;
import com.example.dio.model.CartItem;

import com.example.dio.model.RestaurantTable;
import com.example.dio.model.TableOrder;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.OrderRepository;
import com.example.dio.repository.RestaurantTableRepository;
import com.example.dio.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final RestaurantTableRepository tableRepository;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;


    @Override
    public OrderResponse createOrder(Long tableId) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found with id : " + tableId));



        List<CartItem> cartItems = cartItemRepository.findByIsOrderedAndRestaurantTable_TableId(false,tableId);

        TableOrder tableOrder = new TableOrder();
        tableOrder.setTable(table);
        tableOrder.setCartItems(cartItems);
        tableOrder.setOrderedAt(LocalTime.now());
        tableOrder.setCartItems(cartItems);

        double totalAmount = calculateTotalAmount(cartItems);
        tableOrder.setTotalAmount(totalAmount);
        tableOrder.setOrderStatus(OrderStatus.UN_BILLED);


        orderRepository.save(tableOrder);
        List<Long> cartItemIds = cartItems.stream()
                .map(CartItem::getCartItemId)
                .toList();

        List<CartItemResponse> cartItemResponses = cartItems.stream()
                .map(cartItemMapper::mapToCartItemResponse)
                .toList();


        cartItemRepository.updateCartItemsIsOrdered(cartItemIds);

        return orderMapper.mapToOrderResponse(tableOrder);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        TableOrder tableOrder=orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Order Not Found"));
        return orderMapper.mapToOrderResponse(tableOrder);
    }

    public double calculateTotalAmount(List<CartItem> cartItems) {
        double totalAmount = 0;
        for (CartItem c : cartItems) {
            totalAmount += c.getTotalPrice();
        }
        return totalAmount;
    }
}
