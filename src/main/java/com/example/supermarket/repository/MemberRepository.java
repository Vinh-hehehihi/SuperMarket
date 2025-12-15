package com.example.supermarket.repository;

import com.example.supermarket.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT m FROM Member m WHERE m.isActive = true AND " +
            "(:keyword IS NULL OR :keyword = '' OR m.fullName LIKE %:keyword% OR m.memberCode LIKE %:keyword%)")
    Page<Member> searchMember(String keyword, Pageable pageable);

    Optional<Member> findByMemberCode(String memberCode);
}