package com.example.supermarket.service;

import com.example.supermarket.dto.ProductRequestDTO;
import com.example.supermarket.dto.ProductResponseDTO;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> findAllOrSearch(String keyword);

    ProductRequestDTO getById(Integer id);

    void save(ProductRequestDTO dto);

    void delete(Integer id);


}
