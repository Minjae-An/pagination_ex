package com.example.pagination_ex.controller;

import com.example.pagination_ex.controller.pagination.Pagination;
import com.example.pagination_ex.domain.Member;
import com.example.pagination_ex.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member/{memberId}")
    public ResponseEntity<Member> findMember(@PathVariable Long memberId) {
        Member member = memberService.findById(memberId);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/members")
    public String findAll(int page, int size, Model model) {
        Page<Member> all = memberService.findAll(page, size);
        int pageNumber = all.getNumber();
        int pageSize = all.getSize();
        int totalPages = all.getTotalPages();

        Pagination<Member> memberPagination = new Pagination<>(pageNumber, pageSize, totalPages);
        memberPagination.setContent(all.getContent());

        model.addAttribute("memberPagination", memberPagination);
        return "members";
    }
}
