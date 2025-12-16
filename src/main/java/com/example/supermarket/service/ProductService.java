package com.example.supermarket.service;

import com.example.supermarket.dto.ProductRequestDTO;
import com.example.supermarket.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    // 1. Tìm kiếm và hiển thị danh sách
    List<ProductResponseDTO> findAllOrSearch(String keyword);

    // 2. Lấy chi tiết (dùng cho form sửa)
    ProductRequestDTO getById(Integer id);

    // 3. Lưu (Tạo mới & Cập nhật)
    void save(ProductRequestDTO dto);

    // 4. Xóa
    void delete(Integer id);

}
