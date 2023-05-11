package ru.skillbox.diplom.group35.microservice.streaming.impl.websocket;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.skillbox.diplom.group35.microservice.streaming.impl.service.StreamingService;

/**
 * StreamingHandler
 *
 * @author Marat Safagareev
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StreamingHandler extends TextWebSocketHandler {

  private final StreamingService service;

  @Override
  public void handleTextMessage(@NotNull WebSocketSession session, @NotNull TextMessage message)
      throws IOException {
    log.info("New message: {}", message);
    service.handleMessage(session, message);
  }

  @Override
  public void afterConnectionEstablished(@NotNull WebSocketSession session) {
    log.info("New session: {}", session);
    service.establishConnection(session);
  }

  @Override
  public void afterConnectionClosed(@NotNull WebSocketSession session,
      @NotNull CloseStatus status) {
    log.info("Close session: {}", session);
    service.closeConnection(session);
  }
}
