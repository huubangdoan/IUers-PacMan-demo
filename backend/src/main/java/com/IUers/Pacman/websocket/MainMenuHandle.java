package com.IUers.Pacman.websocket;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MainMenuHandle extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        var node = objectMapper.readTree(payload);
        String command = node.has("action") ? node.get("action").asText() : "";
        switch (command) {
            case "Start" -> sendScreen(session, "MapMenu");
            case "Gacha" -> sendScreen(session, "GachaMenu");
            case "Skin" -> {
                // if (skinMenuPanel != null) skinMenuPanel.refresh();
                // sendScreen(session, "SkinMenu");
            }
            case "Quit" -> {
                // Không có System.exit(0) cho từng client riêng lẻ qua web.
                // Ở đây ta đóng session; tuỳ bạn có thể thêm logic dọn dẹp state phía server.
                sendJson(session, "{\"type\":\"closed\"}");
                session.close(CloseStatus.NORMAL);
            }
            case "Settings" -> sendScreen(session, "Settings");
            default -> sendJson(session, "{\"type\":\"info\",\"message\":\"chua set tinh nang cho nut nay: " + command + "\"}");
        }
    }

    private void sendScreen(WebSocketSession session, String screenName) throws IOException {
        sendJson(session, "{\"type\":\"screen\",\"name\":\"" + screenName + "\"}");
    }

    private void sendJson(WebSocketSession session, String json) throws IOException {
        if (session.isOpen()) {
            session.sendMessage(new TextMessage(json));
        }
    }
}