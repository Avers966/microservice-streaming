package ru.skillbox.diplom.group35.microservice.streaming.impl.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.skillbox.diplom.group35.microservice.streaming.impl.kafka.KafkaSender;
import ru.skillbox.diplom.group35.microservice.streaming.impl.utils.WebSocketContext;
import ru.skillbox.diplom.group35.microservice.streaming.resource.api.dto.AccountOnlineDto;
import ru.skillbox.diplom.group35.microservice.streaming.resource.api.dto.MessageDto;
import ru.skillbox.diplom.group35.microservice.streaming.resource.api.dto.StreamingMessageDto;

/**
 * StreamingService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StreamingService {

  private final ObjectMapper objectMapper;
  private final WebSocketContext webSocketContext;
//  private final KafkaSender kafkaSender;

  public void establishConnection(WebSocketSession session) {
    UUID accountId = getAccountId(session);
    log.info("Connection established with accountId: {}", accountId);
    changeAccountOnline(new AccountOnlineDto(accountId, null, true));
    webSocketContext.addSession(accountId, session);
  }

  public void closeConnection(WebSocketSession session) {
    UUID accountId = getAccountId(session);
    log.info("Connection closed with accountId: {}", accountId);
    changeAccountOnline(new AccountOnlineDto(accountId, ZonedDateTime.now(), false));
    webSocketContext.removeSession(accountId);
  }

  public void handleMessage(WebSocketSession session, TextMessage message) throws IOException {
    session.sendMessage(message); //зеркалочка

    String payload = message.getPayload();
    log.info("Received message: {}", payload);

    JsonNode jsonNode = objectMapper.readTree(payload);
    switch (jsonNode.get("type").textValue()) {
      case "MESSAGE":
        StreamingMessageDto<MessageDto> streamingMessageDto = objectMapper.readValue(payload,
            StreamingMessageDto.class);
        receiveMessage(streamingMessageDto);
        break;
      case "NOTIFICATION":
        //TODO implement notification logic
        break;
    }
  }

  private void changeAccountOnline(AccountOnlineDto accountOnlineDto) {
//    kafkaSender.send(accountOnlineDto);
  }

  private void receiveMessage(StreamingMessageDto<MessageDto> streamingMessageDto) {
    //TODO send to kafka
  }

  private UUID getAccountId(WebSocketSession session) {
    return (UUID) session.getAttributes().get("accountId");
  }
}
