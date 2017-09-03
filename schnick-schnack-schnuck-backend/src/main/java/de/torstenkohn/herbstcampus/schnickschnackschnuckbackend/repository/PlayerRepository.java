package de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.repository;

import de.torstenkohn.herbstcampus.schnickschnackschnuckbackend.model.Player;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface PlayerRepository extends ReactiveMongoRepository<Player, String> {
}
