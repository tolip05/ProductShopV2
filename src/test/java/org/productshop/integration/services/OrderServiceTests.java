package org.productshop.integration.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.productshop.domain.entities.Order;
import org.productshop.domain.entities.Product;
import org.productshop.domain.entities.User;
import org.productshop.domain.models.service.OrderServiceModel;
import org.productshop.domain.models.service.ProductServiceModel;
import org.productshop.domain.models.service.UserServiceModel;
import org.productshop.repositoris.OrderRepository;
import org.productshop.repositoris.ProductRepository;
import org.productshop.service.OrderService;
import org.productshop.service.ProductService;
import org.productshop.service.UserService;
import org.productshop.validation.ProductValidationService;
import org.productshop.validation.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;
    @MockBean
    ProductRepository productRepository;
    @MockBean
    UserService userService;
    @MockBean
    UserValidationService userValidation;
    @MockBean
    ProductValidationService productValidation;



    List<Order> orders;

    @Before
    public void setupTest() {
        orders = new ArrayList<>();
        when(orderRepository.findAll())
                .thenReturn(orders);
    }

    @Test
    public void finedAllOrders_when1Orders_return1Orders() {
//        String customer = "Customer_1";
//        String productImageUrl = "http://www.imageUrl";
//        String productName = "Product_1";
//        BigDecimal productPrice = BigDecimal.valueOf(1.34);
//        Order order = new Order();
//        order.setUser(new User() {{
//            setUsername(customer);
//        }});
//        order.setProduct(new Product() {{
//            setImageUrl(productImageUrl);
//            setName(productName);
//            setPrice(productPrice);
//        }});
//        orders.add(order);
//        var result = orderService.findAllOrders();
//        OrderServiceModel orderServiceModel = result.get(0);
//        Assert.assertEquals(customer, orderServiceModel.getCustomer());
//        Assert.assertEquals(productImageUrl, orderServiceModel.getImageUrl());
//        Assert.assertEquals(productName, orderServiceModel.getName());
//        Assert.assertEquals(productPrice, orderServiceModel.getPrice());
//        Assert.assertEquals(1, result.size());
    }

    @Test
    public void finedAllOrders_when0Orders_returnEmptyOrders() {
        orders.clear();
        var result = orderService.findAllOrders();
        assertTrue(result.isEmpty());
    }

    @Test
    public void createOrder_whenUserAndProductAreValid_orderCreated() throws Exception {
//        when(userValidation.isValid(any()))
//                .thenReturn(true);
//        when(productValidation.isValid(any(Product.class)))
//                .thenReturn(true);
//        when(userService.findUserByUserName(any()))
//                .thenReturn(new UserServiceModel());
//        when(productRepository.findById(any()))
//                .thenReturn(java.util.Optional.of(new Product()));
//        orderService.createOrder("", "");
//        verify(orderRepository).save(any());
    }

//    @Test(expected = Exception.class)
//    public void createOrder_whenUserIsValidAndProductIsNotValid_throw() throws Exception {
//        when(userValidation.isValid(any()))
//                .thenReturn(true);
//        when(productValidation.isValid(any(Product.class)))
//                .thenReturn(false);
//        orderService.createOrder("", "");
//    }

//    @Test(expected = Exception.class)
//    public void createOrder_whenUserIsNotValidAndProductIsValid_throw() throws Exception {
//        when(userValidation.isValid(any()))
//                .thenReturn(false);
//        when(productValidation.isValid(any(Product.class)))
//                .thenReturn(true);
//        orderService.createOrder("", "");
//    }

//    @Test(expected = Exception.class)
//    public void createOrder_whenUserIsNotValidAndProductIsNotValid_throw() throws Exception {
//        when(userValidation.isValid(any()))
//                .thenReturn(false);
//        when(productValidation.isValid(any(Product.class)))
//                .thenReturn(false);
//        orderService.createOrder("", "");
//    }
}

