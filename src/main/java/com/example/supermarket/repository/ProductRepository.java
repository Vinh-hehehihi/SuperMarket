package com.example.supermarket.repository;

import com.example.supermarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(String name, String code);
}
