package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.controller;

import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
public class GameHandler {

  // ===========================================================
  // Constants
  // ===========================================================

  // ===========================================================
  // Fields
  // ===========================================================

  private final GameService service;

  // ===========================================================
  // Constructors
  // ===========================================================

  @Autowired
  public GameHandler(GameService service) {
    this.service = service;
  }

  // ===========================================================
  // Getter & Setter
  // ===========================================================

  // ===========================================================
  // Methods for/from SuperClass/Interfaces
  // ===========================================================

  // ===========================================================
  // Methods
  // ===========================================================
  public Mono<ServerResponse> play(ServerRequest request) {
    final String playerIdOne = request.pathVariable("playerIdOne");
    final String playerIdTwo = request.pathVariable("playerIdTwo");

    return ServerResponse
      .ok()
      .contentType(MediaType.TEXT_EVENT_STREAM)
      .body(this.service.start(playerIdOne, playerIdTwo), String.class);
  }
  // ===========================================================
  // Inner and Anonymous Classes
  // ===========================================================

}
