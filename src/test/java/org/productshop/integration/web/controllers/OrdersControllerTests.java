package org.productshop.integration.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.productshop.domain.entities.Order;
import org.productshop.domain.models.view.OrderViewModel;
import org.productshop.repositoris.OrderRepository;
import org.productshop.web.controllers.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.AssertTrue;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersControllerTests {
    @Autowired
    OrderController controller;

    @Mock
    Principal principal;
    @MockBean
    OrderRepository mockOrderRepository;
    private ArrayList<Order> orders;

    @Before
    public void setup(){
        orders = new ArrayList<>();
//        when(mockOrderRepository.findAllByUser_Username(any()))
//                .thenReturn(orders);
    }
    @Test
    @WithMockUser
    public void getCustomersOrders_whenCustomerHasNoOrders_empty() {
     orders.clear();
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");
        ModelAndView result = controller.getOrdersByName(modelAndView,principal);
        List<OrderViewModel> viewModels = (List<OrderViewModel>) result.getModelMap().get("orders");
        assertTrue(viewModels.isEmpty());
    }

    @Test
    @WithMockUser
    public void getCustomersOrders_whenAllOrdersHasCustomer_empty() {
        orders.addAll(List.of(new Order()));
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");
        ModelAndView result = controller.getOrdersByName(modelAndView,principal);
        List<OrderViewModel> viewModels = (List<OrderViewModel>) result.getModelMap().get("orders");
        assertEquals(orders.size(),viewModels.size());
    }

    @Test
    @WithMockUser
    public void getCustomersOrders_whenAllOrdersNoHasCustomer_empty() {
        orders.addAll(List.of(new Order()));
        ModelAndView modelAndView = new ModelAndView();
        when(principal.getName())
                .thenReturn("");
        ModelAndView result = controller.getOrdersByName(modelAndView,principal);
        List<OrderViewModel> viewModels = (List<OrderViewModel>) result.getModelMap().get("orders");
        assertEquals(orders.size(),viewModels.size());
    }

    }

