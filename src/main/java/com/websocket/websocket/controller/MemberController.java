package com.websocket.websocket.controller;

import com.websocket.websocket.DTO.MemberType;
import com.websocket.websocket.repository.Member;
import com.websocket.websocket.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    public Member save(){
        Member member = new Member();
        member.setId("qwer123");
        member.setType(MemberType.N);
        member.setPassword("1234");
        member.setName("안녕");
        member.setState(true);
        member.setJoinDate(Calendar.getInstance().getTime());
        member.setUpdateDate(Calendar.getInstance().getTime());
        memberService.save(member);
        return member;
    }
}
