package org.productshop.service;

import org.modelmapper.ModelMapper;
import org.productshop.domain.entities.Order;
import org.productshop.domain.entities.Product;
import org.productshop.domain.entities.User;
import org.productshop.domain.models.service.OrderServiceModel;
import org.productshop.domain.models.service.UserServiceModel;
import org.productshop.repositoris.OrderRepository;
import org.productshop.repositoris.ProductRepository;
import org.productshop.validation.ProductValidationService;
import org.productshop.validation.UserValidationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final UserValidationService userValidationService;
    private final ProductValidationService productValidationService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper,
                            ProductRepository productRepository, UserService userService, UserValidationService userValidationService, ProductValidationService productValidationService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userService = userService;
        this.userValidationService = userValidationService;
        this.productValidationService = productValidationService;
    }

    @Override
    public void createOrder(String productId, String name) throws Exception {
        UserServiceModel userServiceModel =
                this.userService.findUserByUserName(name);
        if (!userValidationService.isValid(userServiceModel)){
            throw new Exception();
        }
        Product product = this.productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException());
        if (!productValidationService.isValid(product)){
            throw new Exception();
        }
        User user = new User();
        user.setId(userServiceModel.getId());
        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);
        this.orderRepository.save(order);
    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        return this.orderRepository.findAll()
                .stream()
                .map(order -> this.modelMapper.map(order,OrderServiceModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomerName(String customerName) {
        return this.orderRepository.findAllByUser_Username(customerName)
                .stream()
           //     .filter(order -> order.getUser().getUsername().equals(customerName))
                .map(order -> this.modelMapper.map(order,OrderServiceModel.class))
                .collect(Collectors.toList());
    }
}
