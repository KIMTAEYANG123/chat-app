package com.websocket.websocket.DTO;

import com.websocket.websocket.service.ChatService;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class ChatRoom {
    private String roomId;
    private String name;
//    private Set<WebSocketSession> sessions = new HashSet<>(); //websocket connection이 맺어진 세션

    public static ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name ;
        return chatRoom;
    }


//stomp는 pub/sub방식을 이용하기 때문에 소켓을 관리할 필요가 없데요~

//    public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService){
//        if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)){
//            sessions.add(session);
//            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
//        }else if(chatMessage.getType().equals(ChatMessage.MessageType.EXIT)){
//            sessions.remove(session);
//            chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장하였습니다.");
//        }
//        sendMessage(chatMessage,chatService);
//    }
//    public <T> void sendMessage(T message , ChatService chatService){
//        sessions.parallelStream().forEach(session -> chatService.sendMessage(session,message));
//    }
}
