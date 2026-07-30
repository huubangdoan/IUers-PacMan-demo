package com.IUers.Pacman.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.IUers.Pacman.websocket.MainMenuHandle;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final MainMenuHandle mainMenuHandle;
    public WebSocketConfig(MainMenuHandle mainMenuHandle) {
        this.mainMenuHandle = mainMenuHandle;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
            .addHandler(mainMenuHandle, "/ws/menu")
            .setAllowedOrigins("*");
    }
}