package org.productshop.domain.entities;

import javax.persistence.*;

@Entity(name = "orders")
@Table
public class Order extends BaseEntity{

    private User user;
    private Product product;

    public Order() {
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id",
    referencedColumnName = "id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id",
    referencedColumnName = "id")
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
