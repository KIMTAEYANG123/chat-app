package com.websocket.websocket.configuration;

import com.websocket.websocket.handler.WebSockChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final WebSockChatHandler webSockChatHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSockChatHandler, "/ws/chat").setAllowedOrigins("*");
    }
}
