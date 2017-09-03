package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend;

import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Avatar;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Player;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;


@Component
public class PlayerInitCommandLineRunner implements CommandLineRunner {

  // ===========================================================
  // Constants
  // ===========================================================

  // ===========================================================
  // Fields
  // ===========================================================

  private final PlayerRepository repository;

  // ===========================================================
  // Constructors
  // ===========================================================

  @Autowired
  public PlayerInitCommandLineRunner(PlayerRepository repository) {
    this.repository = repository;
  }

  // ===========================================================
  // Getter & Setter
  // ===========================================================

  // ===========================================================
  // Methods for/from SuperClass/Interfaces
  // ===========================================================

  private static Player createPlayer(final String name) {
    final Avatar avatar = new Avatar(String.valueOf(name.hashCode()));
    final String playerId = UUID.randomUUID().toString();
    return new Player(playerId, name, avatar);
  }


  // ===========================================================
  // Methods
  // ===========================================================

  @Override
  public void run(String... strings) throws Exception {
    this.repository
      .deleteAll()
      .thenMany(
        Flux.just("Peter", "Martin", "Uwe", "Kai")
          .map(PlayerInitCommandLineRunner::createPlayer)
          .flatMap(repository::save))
      .subscribe(null, null,
        () -> repository.findAll().subscribe(System.out::println));
  }

  // ===========================================================
  // Inner and Anonymous Classes
  // ===========================================================

}
