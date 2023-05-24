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
public class WebSocketPool {

  private final ConcurrentHashMap<UUID, WebSocketSession> websocketPool = new ConcurrentHashMap<>();

  public boolean poolContains(UUID accountId) {
    return websocketPool.containsKey(accountId);
  }

  public void addSession(UUID accountId, WebSocketSession session) {
    websocketPool.put(accountId, session);
  }

  public WebSocketSession getSession(UUID accountId) {
    return websocketPool.get(accountId);
  }

  public void removeSession(UUID accountId) {
    websocketPool.remove(accountId);
  }
}
