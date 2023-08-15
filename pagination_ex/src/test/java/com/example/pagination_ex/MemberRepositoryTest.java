package com.example.pagination_ex;

import com.example.pagination_ex.domain.Member;
import com.example.pagination_ex.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    private final String PATH="C:\\pagination_ex\\pagination_ex\\src" +
            "\\test\\java\\com\\example\\pagination_ex\\member_mock_data.json";

    @BeforeEach
    @Commit
    void before(){
        memberRepository.deleteAll();


        List<Member> members = getMemberMockData();
        memberRepository.saveAll(members);
    }

    @Test
    void shouldGetMemberPage(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Member> all = memberRepository.findAll(pageRequest);


        log.info("total page number = {}", all.getTotalPages());
        log.info("current slice number = {}", all.getNumber());
        log.info("page size = {}", all.getSize());
        log.info("total elements number = {}", all.getTotalElements());
        for (Member member : all) {
            log.info("member = {}", member.toString());
        }
    }

    private List<Member> getMemberMockData(){
        File file = new File(PATH);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Member> members;
        try {
            members = Arrays.stream(objectMapper.readValue(file, Member[].class)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return members;
    }
}
