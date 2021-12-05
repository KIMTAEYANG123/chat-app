package com.websocket.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websocket.websocket.DTO.ChatMessage;
import com.websocket.websocket.DTO.ChatRoom;
import com.websocket.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    /**
    *
    * WebSockChatHandler의 설명을 여기에 작성
    *
    * @author USER
    * @param
    * @작성일  2021-12-05
    **/
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//
//        String payload = message.getPayload(); //        클라이언트 측에서 보내는 요청
//        log.info("payload {}", payload);
//
//        ChatMessage chatMessage = objectMapper.readValue(payload,ChatMessage.class); // String to java object
//        log.info("chatMessage {}", chatMessage);
//
//        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
//        room.handleActions(session,chatMessage,chatService);
//    }

    private static List<WebSocketSession> list = new ArrayList<>();

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            String payload = message.getPayload();
            log.info("payload : " + payload);

            for(WebSocketSession sess: list) {
                sess.sendMessage(message);
            }
        }

        /* Client가 접속 시 호출되는 메서드 */
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {

            list.add(session);

            log.info(session + " 클라이언트 접속");
        }

        /* Client가 접속 해제 시 호출되는 메서드드 */

        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

            log.info(session + " 클라이언트 접속 해제");
            list.remove(session);
        }
    }
