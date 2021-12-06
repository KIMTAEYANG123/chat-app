package com.websocket.websocket.configuration;

import com.websocket.websocket.handler.WebSockChatHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//
//@RequiredArgsConstructor
//@Configuration
//@EnableWebSocket
//@Log4j2
//public class WebsocketConfig implements WebSocketConfigurer {
//
//    private final WebSockChatHandler webSockChatHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        log.info("여기는 웹소켓 핸들러 ");
//        registry.addHandler(webSockChatHandler, "/ws/chat").setAllowedOrigins("http:localhost:8080");
//        //setAllowedOrigins("*")에서 *라는 와일드 카드를 사용하면
//        //보안상의 문제로 전체를 허용하는 것보다 직접 하나씩 지정해주어야 한다고 한다.;
//    }
//}
