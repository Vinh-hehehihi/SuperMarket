package com.example.supermarket.validator;

import com.example.supermarket.dto.SupplierRequestDTO;
import com.example.supermarket.entity.Supplier;
import com.example.supermarket.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SupplierValidator implements Validator {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return SupplierRequestDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SupplierRequestDTO requestDTO = (SupplierRequestDTO) target;

        // CHECK TRÙNG SỐ ĐIỆN THOẠI
        // Chỉ check khi số điện thoại đã nhập và không rỗng
        if (requestDTO.getPhone() != null && !requestDTO.getPhone().isEmpty()) {
            Optional<Supplier> existingSupplier = supplierRepository.findByPhone(requestDTO.getPhone());

            if (existingSupplier.isPresent()) {
                // Nếu đang tạo mới (ID là null) mà tìm thấy số -> LỖI
                if (requestDTO.getSupplierID() == null) {
                    errors.rejectValue("phone", "Duplicate.phone", "Số điện thoại đã tồn tại trên hệ thống!");
                }
                // Nếu đang sửa (ID có giá trị)
                else {
                    // Số tìm thấy có ID khác với ID đang sửa -> LỖI (Trùng với người khác)
                    if (!existingSupplier.get().getSupplierID().equals(requestDTO.getSupplierID())) {
                        errors.rejectValue("phone", "Duplicate.phone", "Số điện thoại này thuộc về NCC khác!");
                    }
                }
            }
        }
    }
}