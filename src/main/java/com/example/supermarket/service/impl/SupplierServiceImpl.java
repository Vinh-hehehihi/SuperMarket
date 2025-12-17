package com.example.supermarket.service.impl;

import com.example.supermarket.dto.SupplierRequestDTO;
import com.example.supermarket.dto.SupplierResponseDTO;
import com.example.supermarket.entity.Supplier;
import com.example.supermarket.repository.SupplierRepository;
import com.example.supermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    // Mapper
    private SupplierResponseDTO mapToResponseDTO(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();

        dto.setSupplierID(supplier.getSupplierID());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());

        return dto;
    }


    @Override
    public List<SupplierResponseDTO> findAllOrSearch(String keyword) {
        List<Supplier> suppliers;

        if (keyword != null && !keyword.trim().isEmpty()) {
            suppliers = supplierRepository.findBySupplierNameContainingIgnoreCase(keyword.trim());
        } else {

            suppliers = supplierRepository.findAll();
        }

        // Chuyển đổi danh sách Entities sang danh sách DTOs trước khi trả về
        return suppliers.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDTO save(SupplierRequestDTO requestDTO, Integer supplierId) {
        Supplier supplier;

        if (supplierId != null) {
            //UPDATE
            supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new RuntimeException("Nhà Cung Cấp không tìm thấy với ID: " + supplierId));
        } else {
            //CREATE
            supplier = new Supplier();
            // Thiết lập mã code đơn giản khi tạo mới
            supplier.setSupplierCode("SUP-" + System.currentTimeMillis());
        }

        // Ánh xạ dữ liệu từ Request DTO vào Entity
        supplier.setSupplierName(requestDTO.getSupplierName());
        supplier.setAddress(requestDTO.getAddress());
        supplier.setPhone(requestDTO.getPhone());
        supplier.setContactName(requestDTO.getContactName());
        supplier.setEmail(requestDTO.getEmail());

        // Lưu Entity và trả về DTO đã lưu
        return mapToResponseDTO(supplierRepository.save(supplier));
    }

    @Override
    public SupplierRequestDTO getRequestDTOById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nhà Cung Cấp không tìm thấy với ID: " + id));

        SupplierRequestDTO dto = new SupplierRequestDTO();
        dto.setSupplierCode(supplier.getSupplierCode());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
        dto.setContactName(supplier.getContactName());
        dto.setEmail(supplier.getEmail());
        return dto;
    }


    @Override
    public void delete(Integer id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}