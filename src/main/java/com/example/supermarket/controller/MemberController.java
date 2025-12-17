package com.example.supermarket.controller;

import com.example.supermarket.dto.MemberDTO;
import com.example.supermarket.entity.Member;
import com.example.supermarket.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/members")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @GetMapping
    public String listMembers(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Member> memberPage = memberService.search(keyword, PageRequest.of(page, 5));

        model.addAttribute("members", memberPage);
        model.addAttribute("keyword", keyword);
        return "member/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "member/form";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute MemberDTO dto) {
        memberService.createMember(dto);
        return "redirect:/admin/members";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        MemberDTO dto = memberService.getMemberDetail(id);
        model.addAttribute("memberDTO", dto);
        return "member/form";
    }

    @PostMapping("/update")
    public String updateMember(@ModelAttribute MemberDTO dto) {
        memberService.updateMember(dto);
        return "redirect:/admin/members";
    }

    @GetMapping("/detail/{id}")
    public String viewMemberDetail(@PathVariable Integer id, Model model) {
        MemberDTO dto = memberService.getMemberDetail(id);
        model.addAttribute("member", dto);
        return "member/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return "redirect:/admin/members";
    }
}