package com.example.supermarket.service;

import com.example.supermarket.dto.SupplierRequestDTO;
import com.example.supermarket.dto.SupplierResponseDTO;
import com.example.supermarket.entity.Supplier;

import java.util.List;

public interface SupplierService {
    List<SupplierResponseDTO> findAllOrSearch(String keyword);

    SupplierResponseDTO save(SupplierRequestDTO requestDTO, Integer supplierId);

    SupplierRequestDTO getRequestDTOById(Integer id);

    void delete(Integer id);

    List<Supplier> getAllSuppliers();
}