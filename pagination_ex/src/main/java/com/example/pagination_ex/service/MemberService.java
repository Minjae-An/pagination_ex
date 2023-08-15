package com.example.pagination_ex.service;

import com.example.pagination_ex.domain.Member;
import com.example.pagination_ex.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("해당 멤버가 존재하지 않습니다."));

        return member;
    }

    public Page<Member> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return memberRepository.findAll(pageRequest);
    }
}
