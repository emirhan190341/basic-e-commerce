package com.emirhanarici.ecommerceproject.repository;


import com.emirhanarici.ecommerceproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
