package org.productshop.domain.models.view;

import org.modelmapper.ModelMapper;
import org.productshop.mapings.IHaveCustomMappings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderViewModel {
    private String id;
    private List<ProductDetailsViewModel> products;
    private UserProfileViewModel customer;
    private BigDecimal totalPrice;
    private LocalDateTime finishedOn;


    public OrderViewModel() {
        this.products = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductDetailsViewModel> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductDetailsViewModel> products) {
        this.products = products;
    }

    public UserProfileViewModel getCustomer() {
        return this.customer;
    }

    public void setCustomer(UserProfileViewModel customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getFinishedOn() {
        return this.finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }
}
