package ru.skillbox.diplom.group35.microservice.streaming.impl.websocket;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import ru.skillbox.diplom.group35.library.core.utils.SecurityUtil;
import ru.skillbox.diplom.group35.microservice.streaming.impl.service.StreamingService;

/**
 * StreamingHandshakeHandler
 *
 * @author Marat Safagareev
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StreamingHandshakeHandler extends DefaultHandshakeHandler {

  private final SecurityUtil securityUtil;

  @Override
  public Principal determineUser(ServerHttpRequest request, @NotNull WebSocketHandler wsHandler,
      Map<String, Object> attributes) {
    UUID accountId = securityUtil.getAccountDetails().getId();
    log.info("Determined user from handshake request with account id: {}", accountId);
    attributes.put(StreamingService.ACCOUNT_ID_FIELD, accountId);
    return request.getPrincipal();
  }
}
