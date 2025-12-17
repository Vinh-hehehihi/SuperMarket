package com.example.supermarket.controller;

import com.example.supermarket.entity.Member;
import com.example.supermarket.entity.MemberCard;
import com.example.supermarket.repository.MemberCardRepository;
import com.example.supermarket.repository.MemberRepository;
import com.example.supermarket.service.impl.MemberCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin/cards")
public class MemberCardController {

    @Autowired
    private MemberCardService cardService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberCardRepository cardRepository;

    @GetMapping("/view/{memberId}")
    public String viewCardPage(@PathVariable Integer memberId, Model model) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành viên"));

        Optional<MemberCard> card = cardRepository.findByMember_MemberID(memberId);

        model.addAttribute("member", member);
        model.addAttribute("card", card.orElse(null));

        return "card/view";
    }

    @GetMapping("/issue/{memberId}")
    public String issueCard(@PathVariable Integer memberId) {
        cardService.issueCard(memberId);
        return "redirect:/admin/cards/view/" + memberId;
    }

    @GetMapping("/toggle/{cardId}/{memberId}")
    public String toggleCard(@PathVariable Integer cardId, @PathVariable Integer memberId) {
        cardService.toggleLockCard(cardId);
        return "redirect:/admin/cards/view/" + memberId;
    }
}