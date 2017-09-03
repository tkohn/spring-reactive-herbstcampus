package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.service;

import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Player;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.PlayerMove;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Symbol;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;


@Service
public class PlayerService {

  // ===========================================================
  // Constants
  // ===========================================================
  private static final String ROBO_HASH_URL = "https://robohash.org/set_any/bgset_any/";
  // ===========================================================
  // Fields
  // ===========================================================

  private final PlayerRepository repository;

  // ===========================================================
  // Constructors
  // ===========================================================

  @Autowired
  public PlayerService(PlayerRepository repository) {
    this.repository = repository;
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

  public Mono<Player> byId(String playerId) {
    return this.repository.findById(playerId);
  }

  public Flux<Player> all() {
    return this.repository.findAll();
  }

  public Flux<PlayerMove> moves(String playerId) {
    return byId(playerId)
      .flatMapMany(player -> {

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

        Flux<PlayerMove> playerMoves = Flux.fromStream(
          Stream.generate(() -> new PlayerMove(player, Symbol.random()))
        );

        Flux<Tuple2<Long, PlayerMove>> tuple2Flux = Flux.zip(interval, playerMoves);
        return tuple2Flux.map(Tuple2::getT2);

      });
  }

  public Mono<byte[]> avatar(String playerId) {
    return byId(playerId).map(player -> {
        final String url = ROBO_HASH_URL + player.getAvatar().getImageValue() + "?size=100x100";
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, byte[].class);
      }
    );
  }

  // ===========================================================
  // Inner and Anonymous Classes
  // ===========================================================

}
