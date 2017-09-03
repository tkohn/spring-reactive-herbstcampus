package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.controller;

import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Player;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.PlayerMove;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/players")
public class PlayerRestController {

  // ===========================================================
  // Constants
  // ===========================================================

  // ===========================================================
  // Fields
  // ===========================================================

  private final PlayerService service;

  // ===========================================================
  // Constructors
  // ===========================================================

  @Autowired
  public PlayerRestController(PlayerService service) {
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

  @GetMapping
  public Flux<Player> all() {
    return this.service.all();
  }

  @GetMapping("/{playerId}")
  public Mono<Player> byId(@PathVariable String playerId) {
    return this.service.byId(playerId);
  }

  @GetMapping(value = "/{playerId}/avatar", produces = MediaType.IMAGE_PNG_VALUE)
  public Mono<byte[]> avatar(@PathVariable String playerId) {
    return this.service.avatar(playerId);
  }

  @GetMapping(value = "/{playerId}/moves", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<PlayerMove> moves(@PathVariable String playerId) {
    return this.service.moves(playerId);
  }

  // ===========================================================
  // Inner and Anonymous Classes
  // ===========================================================

}
