package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend;

import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.controller.GameHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication
public class SchnickSchnackSchnuckBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchnickSchnackSchnuckBackendApplication.class, args);
  }

  @Bean
  RouterFunction<?> route(GameHandler handler) {
    return RouterFunctions
      .route(GET("/play/{playerIdOne}/{playerIdTwo}"), handler::play);
  }
}
