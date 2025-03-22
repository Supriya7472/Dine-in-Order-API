package com.example.dio.controller;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.service.OrderService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class OrderController {

    private OrderService orderService;
    @PostMapping("/tables/{tableId}/orders")
    public ResponseEntity<ResponseStructure<OrderResponse>> createOrder(@PathVariable Long tableId){
        OrderResponse orderResponse=orderService.createOrder(tableId);
        return ResponseBuilder.created("Order created ",orderResponse);
    }

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<ResponseStructure<OrderResponse>> getOrderById(@PathVariable Long orderId){
        OrderResponse orderResponse=orderService.getOrderById(orderId);
        return ResponseBuilder.found("Order details found ",orderResponse);
    }


}
