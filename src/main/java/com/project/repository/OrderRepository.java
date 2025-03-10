package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository <Order,Integer>{
Order findByOrderId(int id);
 @Query("Select o from Order o join Product p on o.productId=p.productId")
Optional<Order> findByProductName(String name);
 Optional<Order>findByStatus(String status);
}
 