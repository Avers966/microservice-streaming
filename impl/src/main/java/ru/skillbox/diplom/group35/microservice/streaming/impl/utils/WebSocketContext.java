package ru.skillbox.diplom.group35.microservice.streaming.impl.utils;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocketContext
 *
 * @author Marat Safagareev
 */
@Component
public class WebSocketContext {

  private final ConcurrentHashMap<UUID, WebSocketSession> websocketContext = new ConcurrentHashMap<>();

  public boolean contextContains(UUID accountId) {
    return websocketContext.containsKey(accountId);
  }

  public void addSession(UUID accountId, WebSocketSession session) {
    websocketContext.put(accountId, session);
  }

  public WebSocketSession getSession(UUID accountId) {
    return websocketContext.get(accountId);
  }

  public void removeSession(UUID accountId) {
    websocketContext.remove(accountId);
  }
}
