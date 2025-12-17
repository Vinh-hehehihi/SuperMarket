package com.example.supermarket.repository;

import com.example.supermarket.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    boolean existsByInvoiceNumber(String invoiceNumber);

    Optional<Invoice> findByInvoiceNumber(String invoiceNumber);
    Page<Invoice> findByMember_MemberID(Integer memberId, Pageable pageable);

    Page<Invoice> findByEmployee_EmployeeID(Integer employeeId, Pageable pageable);

    Page<Invoice> findByInvoiceDateBetween(Date startDate, Date endDate, Pageable pageable);

    Page<Invoice> findByMember_MemberIDAndEmployee_EmployeeID(
            Integer memberId, Integer employeeId, Pageable pageable
    );
}
