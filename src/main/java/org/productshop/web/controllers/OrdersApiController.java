package org.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.productshop.domain.models.rest.ProductOrderRequestModel;
import org.productshop.service.OrderService;
import org.productshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/order")
public class OrdersApiController {
    private final ProductService productService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrdersApiController(ProductService productService,
                               OrderService orderService, ModelMapper modelMapper) {
        this.productService = productService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/submit")
    public void submitOrder(@RequestBody ProductOrderRequestModel model, Principal principal) throws Exception {
        String name = principal.getName();
        orderService.createOrder(model.getId(),name);
    }
}
