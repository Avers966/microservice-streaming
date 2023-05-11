package ru.skillbox.diplom.group35.microservice.streaming.impl.websocket;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.server.WsSci;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfig
 *
 * @author Marat Safagareev
 */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

  private final StreamingHandler streamingHandler;
  private final StreamingHandshakeHandler streamingHandshakeHandler;

  @Override
  public void registerWebSocketHandlers(@NotNull WebSocketHandlerRegistry registry) {
    registry.addHandler(streamingHandler, "/api/v1/streaming/ws")
        .setHandshakeHandler(streamingHandshakeHandler)
        .setAllowedOriginPatterns("*");
  }

  @Bean
  public TomcatServletWebServerFactory tomcatContainerFactory() {
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
    factory.setTomcatContextCustomizers(Collections.singletonList(tomcatContextCustomizer()));
    return factory;
  }

  @Bean
  public TomcatContextCustomizer tomcatContextCustomizer() {
    return context -> context.addServletContainerInitializer(new WsSci(), null);
  }
}
