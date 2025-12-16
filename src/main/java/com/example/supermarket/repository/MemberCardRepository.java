package com.example.supermarket.repository;

import com.example.supermarket.entity.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberCardRepository extends JpaRepository<MemberCard, Integer> {
    Optional<MemberCard> findByMember_MemberID(Integer memberID);

    boolean existsByCardNumber(String cardNumber);
}