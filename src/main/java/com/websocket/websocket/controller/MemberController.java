package com.websocket.websocket.controller;

import com.websocket.websocket.DTO.MemberType;
import com.websocket.websocket.repository.Member;
import com.websocket.websocket.repository.MemberMappingName;
import com.websocket.websocket.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/save")
    @ResponseBody
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

    @GetMapping("/findById/{memberId}")
    @ResponseBody
    public Member findById(@PathVariable String memberId, Model model){
        Member member = memberService.findById(memberId);
        return member;
    }

    @GetMapping("/findByName/{name}")
    public String findByName(@PathVariable String name, Model model){
        List<Member> list =  memberService.findByName(name);
        model.addAttribute("list", list);
        return "/test";
    }

    @GetMapping("/findNameMappingByName/{name}")
    @ResponseBody
    public List<MemberMappingName> findNameMappingByName(@PathVariable String name){
        return memberService.findNameMappingByName(name);
    }

    @GetMapping("/existByName/{name}")
    @ResponseBody
    public boolean existByName(@PathVariable String name){
        return memberService.existsByName(name);
    }

    @GetMapping("/countByName/{name}")
    @ResponseBody
    public int countByName(@PathVariable String name){
        return memberService.countByName(name);
    }
}
