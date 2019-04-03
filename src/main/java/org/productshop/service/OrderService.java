package org.productshop.service;

import org.productshop.domain.models.service.OrderServiceModel;

import java.util.Arrays;
import java.util.List;

public interface OrderService {
    void createOrder(String productId, String name) throws Exception;
    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findOrdersByCustomerName(String customerName);
}
