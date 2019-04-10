package org.productshop.domain.models.service;

import org.modelmapper.ModelMapper;
import org.productshop.domain.entities.Order;
import org.productshop.domain.entities.Product;
import org.productshop.domain.entities.User;
import org.productshop.mapings.IHaveCustomMappings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceModel extends BaseServiceModel {
    private List<ProductServiceModel> products;
    private UserServiceModel customer;
    private BigDecimal totalPrice;
    private LocalDateTime finishedOn;

    public OrderServiceModel() {
        this.products = new ArrayList<>();
    }

    public List<ProductServiceModel> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }

    public UserServiceModel getCustomer() {
        return this.customer;
    }

    public void setCustomer(UserServiceModel customer) {
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
    //    private String imageUrl;
//    private String name;
//    private BigDecimal price;
//    private String customer;
//
//    public OrderServiceModel() {
//    }
//
//    public String getImageUrl() {
//        return this.imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public BigDecimal getPrice() {
//        return this.price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }
//
//    public String getCustomer() {
//        return this.customer;
//    }
//
//    public void setCustomer(String customer) {
//        this.customer = customer;
//    }
//
//    @Override
//    public void configureMappings(ModelMapper mapper) {
//        mapper.createTypeMap(Order.class,OrderServiceModel.class)
//                .addMapping(
//                        entity-> entity.getProduct().getName(),
//                        (dto,value) -> dto.setName((String)value)
//                )
//                .addMapping(
//                        entity-> entity.getProduct().getPrice(),
//                        (dto,value) -> dto.setPrice((BigDecimal) value)
//                )
//                .addMapping(
//                        entity-> entity.getProduct().getImageUrl(),
//                        (dto,value) -> dto.setImageUrl((String)value)
//                )
//                .addMapping(
//                        entity-> entity.getUser().getUsername(),
//                        (dto,value) -> dto.setCustomer((String)value)
//                );
//    }
}
