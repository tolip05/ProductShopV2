package org.productshop.repositoris;

import org.productshop.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
  //  List<Order>findAllByUser_Username(String username);
    List<Order> findAllOrdersByCustomer_UsernameOrderByFineshedOn(String username);
}
