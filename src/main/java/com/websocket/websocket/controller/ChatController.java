package com.websocket.websocket.controller;

import com.websocket.websocket.DTO.ChatRoom;
import com.websocket.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final ChatService chatService;

    /**
    *
    * 채팅 리스트
    *
    * @author USER
    * @param
    * @작성일  2021-12-06
    **/
    @GetMapping("/list")
    public String chatList(Model model){
        List<ChatRoom> lists = chatService.findAllRoom();
        model.addAttribute("lists", lists);
        return "/chatList";
    }

//    채팅방 입장
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(@PathVariable String roomId , Model model){
        ChatRoom chatRoom = chatService.findRoomById(roomId);
        if(chatRoom == null){
            log.info("채팅방이 존재하지 않습니다.");
            return "redirect:/list";
        }
        model.addAttribute("chatRoom", chatRoom);
        return "/index";
    }

    @PostMapping("/create")
    public String createRoom(@RequestParam String name){
        boolean flag = chatService.createRoom(name);
        if(flag){
            log.info("채팅방 생성 {}" ,name);
        }

        return "redirect:/list";
    }

    @GetMapping("/room")
    public void findAllRoom(String roomId , Model model){
        model.addAttribute("room", chatService.findRoomById(roomId));
    }
}
