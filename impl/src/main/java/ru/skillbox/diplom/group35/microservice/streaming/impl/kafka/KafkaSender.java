package ru.skillbox.diplom.group35.microservice.streaming.impl.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.microservice.streaming.resource.api.dto.AccountOnlineDto;

/**
 * KafkaSender
 *
 * @author Marat Safagareev
 */
@Service
@RequiredArgsConstructor
public class KafkaSender {

//  private final ObjectMapper objectMapper;
//  private final KafkaTemplate<String, String> kafkaTemplate;
//
//  @Value("${app.topic.account}")
//  private String topic;
//
//  public void send(AccountOnlineDto accountOnlineDto) {
//    kafkaTemplate.send(topic, writeValueAsString(accountOnlineDto));
//  }
//
//  private String writeValueAsString(Object o) {
//    String result = null;
//    try {
//      result = objectMapper.writeValueAsString(o);
//    } catch (JsonProcessingException e) {
//      e.printStackTrace();
//    }
//    return result;
//  }


}
