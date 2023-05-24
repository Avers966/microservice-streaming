package ru.skillbox.diplom.group35.microservice.streaming.impl.kafka;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.skillbox.diplom.group35.library.core.dto.streaming.StreamingMessageDto;

/**
 * KafkaConsumerConfig
 *
 * @author Marat Safagareev
 */
@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

  private final KafkaConstConfig kafkaConstConfig;

  public Map<String, Object> consumerConfig() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConstConfig.getBootstrapAddress());
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConstConfig.getGroupId());
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,
        kafkaConstConfig.getMaxPollInterval());
    properties.put(JsonDeserializer.TRUSTED_PACKAGES, kafkaConstConfig.getTrustPackages());
    return properties;
  }

  @Bean
  public ConsumerFactory<String, StreamingMessageDto<?>> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfig());
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, StreamingMessageDto<?>>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, StreamingMessageDto<?>> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setConcurrency(kafkaConstConfig.getConcurrency());
    factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
    return factory;
  }
}
