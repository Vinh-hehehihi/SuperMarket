package com.example.supermarket.repository;

import com.example.supermarket.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByEmployeeCode(String employeeCode);

    @Query("SELECT e FROM Employee e WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR e.fullName LIKE %:keyword% OR e.phone LIKE %:keyword%) " +
            "AND (:role IS NULL OR :role = '' OR e.role = :role) " +
            "AND e.isActive = true")
    Page<Employee> searchEmployee(@Param("keyword") String keyword,
                                  @Param("role") String role,
                                  Pageable pageable);
}