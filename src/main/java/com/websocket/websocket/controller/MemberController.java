package com.websocket.websocket.controller;

import com.websocket.websocket.DTO.MemberType;
import com.websocket.websocket.repository.Member;
import com.websocket.websocket.repository.MemberMappingName;
import com.websocket.websocket.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Log4j2
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

    @GetMapping("/main")
    public String main(){
        return "/login";
    }

    @PostMapping("/login")
    public String main(final String id , final String password , Model model){
        Optional<Member> member = memberService.findById(id);
        log.info("이거다 {}",member);
        if(member == null){
            return "redirect:/user/main";
        }
        if(member.get().getPassword().equals(password)) {
            return "redirect:/list";
        }else{
            return "redirect:/user/main";
        }

    }

//    @GetMapping("/findById/{memberId}")
//    @ResponseBody
//    public boolean findById(@PathVariable String memberId){
//        Optional<Member> member = memberService.findById(memberId);
//        if(member == null){
//            return true;
//        }
//        return false;
//    }

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
