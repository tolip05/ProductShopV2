package org.productshop.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Table
public class Order extends BaseEntity{

    private List<Product> products;
    private User customer;
    private BigDecimal totalPrice;
    private LocalDateTime finishedOn;

    public Order() {
        this.products = new ArrayList<>();
    }

    @ManyToMany(targetEntity = Product.class)
    @JoinTable(
            name = "orders_product",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )
    )
    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id"
    )
    public User getCustomer() {
        return this.customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Column(name = "price")
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getFineshedOn() {
        return this.finishedOn;
    }

    public void setFineshedOn(LocalDateTime fineshedOn) {
        this.finishedOn = fineshedOn;
    }
    //    private User user;
//    private Product product;
//
//    public Order() {
//    }
//
//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "user_id",
//    referencedColumnName = "id")
//    public User getUser() {
//        return this.user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//    @ManyToOne(targetEntity = Product.class)
//    @JoinColumn(name = "product_id",
//    referencedColumnName = "id")
//    public Product getProduct() {
//        return this.product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
