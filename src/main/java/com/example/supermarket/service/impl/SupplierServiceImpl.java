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

@Service // Đánh dấu đây là Service Component
public class SupplierServiceImpl implements SupplierService { // implements Interface

    // Sử dụng @Autowired để tiêm Repository Interface
    @Autowired
    private SupplierRepository supplierRepository;

    // --- Mapper (Hàm chuyển đổi) ---
    // Hàm này giúp chuyển đổi Entity sang Response DTO để gửi ra Controller
    private SupplierResponseDTO mapToResponseDTO(Supplier supplier) {
        SupplierResponseDTO dto = new SupplierResponseDTO();

        // Gán các trường cần thiết cho Output
        dto.setSupplierID(supplier.getSupplierID());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
        // Thêm các trường khác nếu cần hiển thị

        return dto;
    }

    // --- Các phương thức triển khai từ SupplierService Interface ---

    /**
     * Chức năng READ ALL & SEARCH Nhà Cung Cấp
     * @param keyword Từ khóa tìm kiếm (theo tên)
     * @return List<SupplierResponseDTO>
     */
    @Override
    public List<SupplierResponseDTO> findAllOrSearch(String keyword) {
        List<Supplier> suppliers;

        if (keyword != null && !keyword.trim().isEmpty()) {
            // Tìm kiếm theo tên NCC
            suppliers = supplierRepository.findBySupplierNameContainingIgnoreCase(keyword.trim());
        } else {
            // Lấy tất cả
            suppliers = supplierRepository.findAll();
        }

        // Chuyển đổi danh sách Entities sang danh sách DTOs trước khi trả về
        return suppliers.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Chức năng CREATE / UPDATE Nhà Cung Cấp
     * @param requestDTO Dữ liệu đầu vào từ form
     * @param supplierId ID (null nếu tạo mới)
     * @return SupplierResponseDTO của đối tượng đã lưu
     */
    @Override
    public SupplierResponseDTO save(SupplierRequestDTO requestDTO, Integer supplierId) {
        Supplier supplier;

        if (supplierId != null) {
            // Case 1: UPDATE (Tìm kiếm entity cũ để cập nhật)
            supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new RuntimeException("Nhà Cung Cấp không tìm thấy với ID: " + supplierId));
        } else {
            // Case 2: CREATE (Tạo entity mới)
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

    /**
     * Lấy dữ liệu NCC theo ID, dùng để điền vào form chỉnh sửa
     * @param id ID của NCC
     * @return SupplierRequestDTO (chỉ chứa các trường dùng cho Input)
     */
    @Override
    public SupplierRequestDTO getRequestDTOById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nhà Cung Cấp không tìm thấy với ID: " + id));

        // Chuyển Entity sang Request DTO
        SupplierRequestDTO dto = new SupplierRequestDTO();
        dto.setSupplierCode(supplier.getSupplierCode());
        dto.setSupplierName(supplier.getSupplierName());
        dto.setPhone(supplier.getPhone());
        dto.setAddress(supplier.getAddress());
        dto.setContactName(supplier.getContactName());
        dto.setEmail(supplier.getEmail());
        return dto;
    }

    /**
     * Chức năng DELETE Nhà Cung Cấp
     * @param id ID của NCC cần xóa
     */
    @Override
    public void delete(Integer id) {
        // Có thể thêm logic kiểm tra ràng buộc ở đây (ví dụ: NCC này còn phiếu nhập không?)
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        // Đơn giản là lấy tất cả từ DB trả về để Controller ném vào Dropdown
        return supplierRepository.findAll();
    }
}