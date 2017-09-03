package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.service;

import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.GameEvent;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Player;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.PlayerMove;
import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;


@Service
public class GameService {

  // ===========================================================
  // Constants
  // ===========================================================

  // ===========================================================
  // Fields
  // ===========================================================

  private final PlayerService playerService;

  // ===========================================================
  // Constructors
  // ===========================================================

  @Autowired
  public GameService(PlayerService playerService) {
    this.playerService = playerService;
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

  public Flux<String> start(final String playerIdOne, final String playerIdTwo) {
    return playerService.moves(playerIdOne)
      .zipWith(playerService.moves(playerIdTwo))
      .map(tuple2 -> new GameEvent(tuple2.getT1(), tuple2.getT2()))
      .map(event -> {
        final Symbol one = event.getPlayerOneMove().getMove();
        final Symbol two = event.getPlayerTwoMove().getMove();

        if (one.equals(two)) {
          return String.format("%s vs %s -> DRAW", one, two);
        } else {
          final Tuple2<Player, String> tuple2 = analyseWinner(event.getPlayerOneMove(), event.getPlayerTwoMove());
          return String.format("%s vs %s -> %s | %s WINS", one, two, tuple2.getT2(), tuple2.getT1().getName());
        }
      });
  }

  private Tuple2<Player, String> analyseWinner(final PlayerMove moveOne, final PlayerMove moveTwo) {
    final Symbol one = moveOne.getMove();
    final Symbol two = moveTwo.getMove();
    final Player winner = Symbol.firstWins(one, two) ? moveOne.getPlayer() : moveTwo.getPlayer();
    return Tuples.of(winner, getText(one, two));
  }

  private String getText(final Symbol one, final Symbol two) {
    switch (one) {
      case Spock:
        if (Symbol.Scissors.equals(two)) return "Spock smashes Scissors";
        if (Symbol.Rock.equals(two)) return "Spock vaporizes Rock";
        if (Symbol.Paper.equals(two)) return "Paper disproves Spock";
        if (Symbol.Lizard.equals(two)) return "Lizard poisons Spock";
        break;
      case Rock:
        if (Symbol.Scissors.equals(two)) return "Rock crushes Scissors";
        if (Symbol.Lizard.equals(two)) return "Rock crushes Lizard";
        if (Symbol.Paper.equals(two)) return "Paper covers Rock";
        if (Symbol.Spock.equals(two)) return "Spock vaporizes Rock";
        break;
      case Paper:
        if (Symbol.Spock.equals(two)) return "Paper disproves Spock";
        if (Symbol.Rock.equals(two)) return "Paper covers Rock";
        if (Symbol.Scissors.equals(two)) return "Scissors cuts Paper";
        if (Symbol.Lizard.equals(two)) return "Lizard eats Paper";
        break;
      case Lizard:
        if (Symbol.Spock.equals(two)) return "Lizard poisons Spock";
        if (Symbol.Paper.equals(two)) return "Lizard eats Paper";
        if (Symbol.Scissors.equals(two)) return "Scissors decapitates Lizard";
        if (Symbol.Rock.equals(two)) return "Rock crushes Lizard";
        break;
      case Scissors:
        if (Symbol.Paper.equals(two)) return "Scissors cuts Paper";
        if (Symbol.Lizard.equals(two)) return "Scissors decapitates Lizard";
        if (Symbol.Spock.equals(two)) return "Spock smashes Scissors";
        if (Symbol.Rock.equals(two)) return "Rock crushes Scissors";
    }

    return "somebody cheated";
  }

  // ===========================================================
  // Inner and Anonymous Classes
  // ===========================================================

}
