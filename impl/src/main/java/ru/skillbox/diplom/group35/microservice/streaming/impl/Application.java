package ru.skillbox.diplom.group35.microservice.streaming.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import ru.skillbox.diplom.group35.library.core.annotation.EnableSecurity;
import ru.skillbox.diplom.group35.library.core.annotation.JwtProvider;

/**
 * Application
 *
 * @author Marat Safagareev
 */
@JwtProvider
@EnableSecurity
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
