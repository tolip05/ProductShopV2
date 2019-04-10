package org.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.productshop.domain.models.rest.ProductOrderRequestModel;
import org.productshop.domain.models.service.ProductServiceModel;
import org.productshop.domain.models.view.OrderViewModel;
import org.productshop.domain.models.view.ProductDetailsViewModel;
import org.productshop.service.OrderService;
import org.productshop.service.ProductService;
import org.productshop.web.controllers.anotation.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
private final ProductService productService;
private final OrderService orderService;
private final ModelMapper modelMapper;

@Autowired
    public OrderController(ProductService productService, OrderService orderService, ModelMapper modelMapper) {
        this.productService = productService;
    this.orderService = orderService;
    this.modelMapper = modelMapper;
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Product")
    public ModelAndView orderProduct(@PathVariable String id, ModelAndView modelAndView,
                                     Principal principal) {
        ProductServiceModel model = this.productService.findProductById(id);
        ProductDetailsViewModel product = this.modelMapper.map(model,ProductDetailsViewModel.class);
    modelAndView.addObject("product",product);
    modelAndView.addObject("customer",principal.getName());
        return view("order/order-product", modelAndView);
    }

//    @PostMapping("/order")
//    @PreAuthorize("isAuthenticated()")
//    public ModelAndView setOrder(@ModelAttribute ProductOrderRequestModel model, Principal principal) throws Exception {
//        String name = principal.getName();
//        orderService.createOrder(model.getId(), name);
//        return view("home");
//    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle("Product List")
    public ModelAndView getAllOrders(ModelAndView modelAndView){
        List<OrderViewModel> orderViewModels = this.orderService.findAllOrders()
                .stream().map(order-> this.modelMapper
                .map(order,OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders",orderViewModels);

    return view("order/all-orders",modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("My Product")
    public ModelAndView getMyOrders(ModelAndView modelAndView,Principal principal){
        List<OrderViewModel> orderViewModels = this.orderService
                .findOrdersByCustomerName(principal.getName())
                .stream().map(order-> this.modelMapper
                        .map(order,OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders",orderViewModels);

        return view("order/all-orders",modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Details")
    public ModelAndView allOrders(@PathVariable String id,ModelAndView modelAndView){
    modelAndView.addObject("order",this.modelMapper.map(this.orderService.finedOrderById(id),OrderViewModel.class));
     return super.view("/order/order-details",modelAndView);
    }

    @GetMapping("/customer")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Customer")
    public ModelAndView getOrdersByName(ModelAndView modelAndView,Principal principal){
        String customerName = principal.getName();
    List<OrderViewModel> orderViewModels = this.orderService.findOrdersByCustomerName(customerName)
                .stream().map(order-> this.modelMapper
                        .map(order,OrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders",orderViewModels);
        return view("order/list-orders",modelAndView);
    }
}
