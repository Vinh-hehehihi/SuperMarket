package com.example.supermarket.service.impl;

import com.example.supermarket.entity.Member;
import com.example.supermarket.entity.MemberCard;
import com.example.supermarket.repository.MemberCardRepository;
import com.example.supermarket.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class MemberCardService {

    @Autowired
    private MemberCardRepository cardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void issueCard(Integer memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành viên"));

        if (cardRepository.findByMember_MemberID(memberId).isPresent()) {
            throw new RuntimeException("Thành viên này đã có thẻ!");
        }

        MemberCard card = new MemberCard();
        card.setMember(member);
        card.setIsActive(true);

        card.setCardNumber(generateUniqueCardNumber());

        Date now = new Date();
        card.setIssuedAt(now);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, 1);
        card.setExpiryAt(calendar.getTime());

        cardRepository.save(card);
    }

    public void toggleLockCard(Integer cardId) {
        MemberCard card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thẻ"));

        card.setIsActive(!card.getIsActive());
        cardRepository.save(card);
    }

    private String generateUniqueCardNumber() {
        Random random = new Random();
        String code;
        do {
            int number = 100000 + random.nextInt(900000); // Số từ 100000 đến 999999
            code = "CARD-" + number;
        } while (cardRepository.existsByCardNumber(code));
        return code;
    }
}