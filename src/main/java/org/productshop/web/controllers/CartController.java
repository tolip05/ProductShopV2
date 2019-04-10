package org.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.productshop.domain.models.service.OrderServiceModel;
import org.productshop.domain.models.service.ProductServiceModel;
import org.productshop.domain.models.view.ProductDetailsViewModel;
import org.productshop.domain.models.view.ShoppingCartItem;
import org.productshop.service.OrderService;
import org.productshop.service.ProductService;
import org.productshop.service.UserService;
import org.productshop.web.controllers.anotation.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartController(ProductService productService, UserService userService,
                          OrderService orderService, ModelMapper modelMapper) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add-product")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addToCartConfirm(String id, int quantity, HttpSession session) {

        this.initCart(session);
        ProductDetailsViewModel product =
                this.modelMapper.map(this.productService
                        .findProductById(id),ProductDetailsViewModel.class);
        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setQuantity(quantity);
        cartItem.setProduct(product);
        this.addItemToCart(cartItem,session);
        return super.redirect("/home");
    }

    @PostMapping("/details")
    public ModelAndView cartDetailsConfirm(ModelAndView modelAndView,HttpSession session){
        this.initCart(session);
    modelAndView.addObject("totalPrice",this.calcTotal((List<ShoppingCartItem>)session.getAttribute("shopping-cart")));
      return super.view("/cart/cart-details",modelAndView);
    }

    @GetMapping("/details")
    @PageTitle("Cart details")
    public ModelAndView cartDetails(ModelAndView modelAndView,HttpSession session){
        this.initCart(session);
        modelAndView.addObject("totalPrice",this.calcTotal((List<ShoppingCartItem>)session.getAttribute("shopping-cart")));
        return super.view("/cart/cart-details",modelAndView);
    }

    @PostMapping("/remove-product")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView removeFromCartProduct(String id,HttpSession session){
        this.deleteItemFromCart(id,session);
        return super.redirect("/cart/details");
    }

    @PostMapping("/checkout")
    public ModelAndView checkoutConfirm(HttpSession session, Principal principal) throws Exception {
        List<ShoppingCartItem> cart = this.retrieveCart(session);
        OrderServiceModel orderServiceModel = this.prepareOrder(cart,principal.getName());
       this.orderService.createOrder(orderServiceModel);
        return super.redirect("/home");
    }

    private void initCart(HttpSession session) {
        if (session.getAttribute("shopping-cart") == null) {
            session.setAttribute("shopping-cart", new LinkedList<>());
        }
    }
    private void addItemToCart(ShoppingCartItem item,HttpSession session){
        for (ShoppingCartItem cartItem : ((List<ShoppingCartItem>)session.getAttribute("shopping-cart"))) {
            if (cartItem.getProduct().getId().equals(item.getProduct().getId())){
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        ((List<ShoppingCartItem>)session.getAttribute("shopping-cart"))
                .add(item);
    }
    private void deleteItemFromCart(String id,HttpSession session){
        ((List<ShoppingCartItem>)session.getAttribute("shopping-cart"))
                .removeIf(ci-> ci.getProduct().getId().equals(id));

    }
    private List<ShoppingCartItem> retrieveCart(HttpSession session) {
        this.initCart(session);

        return (List<ShoppingCartItem>) session.getAttribute("shopping-cart");
    }

    private OrderServiceModel prepareOrder(List<ShoppingCartItem> cart,String customer){
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        orderServiceModel.setCustomer(this.userService.findUserByUserName(customer));
        List<ProductServiceModel>products = new ArrayList<>();
        for (ShoppingCartItem item : cart) {
            ProductServiceModel productServiceModel =
                    this.modelMapper.map(item.getProduct(),ProductServiceModel.class);


            for (int i = 0; i < item.getQuantity(); i++) {
                products.add(productServiceModel);
            }

        }
        orderServiceModel.setProducts(products);
        orderServiceModel.setTotalPrice(this.calcTotal(cart));
        return orderServiceModel;
    }
    private BigDecimal calcTotal(List<ShoppingCartItem> cart){
        BigDecimal result = new BigDecimal(0);
        for (ShoppingCartItem cartItem : cart) {
            result = result.add(cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        }
        return result;
    }
}
