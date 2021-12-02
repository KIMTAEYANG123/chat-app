package com.websocket.websocket.DTO;

import lombok.Data;

@Data
public class ChatMessage {

    public enum MessageType { ENTER, TALK}

    private MessageType type; //메세지 타입
    private String roomId;
    private String sender;
    private String message;

}
