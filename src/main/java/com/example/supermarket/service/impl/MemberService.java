package com.example.supermarket.service.impl;

import com.example.supermarket.dto.MemberDTO;
import com.example.supermarket.entity.Member;
import com.example.supermarket.repository.MemberRepository;
import com.example.supermarket.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Page<Member> search(String keyword, Pageable pageable) {
        return memberRepository.searchMember(keyword, pageable);
    }

    @Override
    public Member createMember(MemberDTO dto) {
        if (memberRepository.findByMemberCode(dto.getMemberCode()).isPresent()) {
            throw new RuntimeException("Mã thành viên đã tồn tại");
        }

        Member member = new Member();
        member.setMemberCode(dto.getMemberCode().trim().toUpperCase());
        member.setFullName(dto.getFullName().trim().replaceAll("\\s{2,}", " "));
        member.setGender(dto.getGender());
        member.setBirthDate(dto.getBirthDate());
        member.setLoyaltyPoints(0);
        member.setIsActive(true);

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(MemberDTO dto) {
        Member existingMember = memberRepository.findById(dto.getMemberID())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành viên"));

        existingMember.setFullName(dto.getFullName().trim().replaceAll("\\s{2,}", " "));
        existingMember.setGender(dto.getGender());
        existingMember.setBirthDate(dto.getBirthDate());

        return memberRepository.save(existingMember);
    }

    @Override
    public Member deleteMember(Integer id) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành viên"));
        existingMember.setIsActive(false); // Xóa mềm
        return memberRepository.save(existingMember);
    }

    @Override
    public MemberDTO getMemberDetail(Integer id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành viên ID: " + id));

        MemberDTO dto = new MemberDTO();
        dto.setMemberID(member.getMemberID());
        dto.setMemberCode(member.getMemberCode());
        dto.setFullName(member.getFullName());
        dto.setGender(member.getGender());
        dto.setBirthDate(member.getBirthDate());
        dto.setLoyaltyPoints(member.getLoyaltyPoints());
        dto.setMemberCard(member.getMemberCard());
        dto.setIsActive(member.getIsActive());

        return dto;
    }
}