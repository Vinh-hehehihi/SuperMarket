package com.example.supermarket.service;

import com.example.supermarket.dto.SupplierRequestDTO;
import com.example.supermarket.dto.SupplierResponseDTO;
import com.example.supermarket.entity.Supplier;

import java.util.List;

public interface SupplierService {

    // Chức năng READ ALL & SEARCH
    List<SupplierResponseDTO> findAllOrSearch(String keyword);

    // Chức năng CREATE / UPDATE
    SupplierResponseDTO save(SupplierRequestDTO requestDTO, Integer supplierId);

    // Chức năng GET BY ID (Dùng cho Edit/Detail)
    SupplierRequestDTO getRequestDTOById(Integer id);

    // Chức năng DELETE
    void delete(Integer id);


    List<Supplier> getAllSuppliers();
}