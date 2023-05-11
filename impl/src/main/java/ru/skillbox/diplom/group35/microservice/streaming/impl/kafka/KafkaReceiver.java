package ru.skillbox.diplom.group35.microservice.streaming.impl.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * KafkaReceiver
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@EnableKafka
public class KafkaReceiver {

//  @KafkaListener(topics = "${app.topic.streaming}")
  public void receive(String message) {
    log.info("received message='{}'", message);
  }
}
