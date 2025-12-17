package com.example.supermarket.repository;

import com.example.supermarket.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findBySupplierNameContainingIgnoreCase(String keyword);
    Optional<Supplier> findByPhone(String phone);
    Optional<Supplier> findBySupplierCode(String supplierCode);
}

