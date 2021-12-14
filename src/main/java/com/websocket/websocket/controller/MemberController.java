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

    @GetMapping("/join")
    public String join(Member member, Model model){
        model.addAttribute("member", member);
        return "/userJoin";
    }

    @PostMapping("/save")
    public String save(final Member member){
        member.setType(MemberType.N);
        member.setState(true);
        member.setJoinDate(Calendar.getInstance().getTime());
        member.setUpdateDate(Calendar.getInstance().getTime());
        memberService.save(member);
        return "redirect:/user/join";
    }

    @GetMapping("/findById/{memberId}")
    @ResponseBody
    public boolean findById(@PathVariable String memberId){
        Member member = memberService.findById(memberId);
        if(member == null){
            return true;
        }
        return false;
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

    @GetMapping("/existById/{id}")
    @ResponseBody
    public boolean existById(@PathVariable String id){
        boolean exist = memberService.existsById(id);
        if(exist){
            return true;
        }
        return false;
    }

    @GetMapping("/countByName/{name}")
    @ResponseBody
    public int countByName(@PathVariable String name){
        return memberService.countByName(name);
    }
}
