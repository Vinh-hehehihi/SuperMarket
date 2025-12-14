package com.example.supermarket.service;

import com.example.supermarket.dto.MemberDTO;
import com.example.supermarket.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMemberService {
    Page<Member> search(String keyword, Pageable pageable);
    Member createMember(MemberDTO dto);
    Member updateMember(MemberDTO dto);
    Member deleteMember(Integer id);
    MemberDTO getMemberDetail(Integer id);
}