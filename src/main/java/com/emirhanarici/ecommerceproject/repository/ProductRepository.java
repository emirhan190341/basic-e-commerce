package com.emirhanarici.ecommerceproject.repository;


import com.emirhanarici.ecommerceproject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
