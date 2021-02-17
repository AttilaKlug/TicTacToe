package hu.mydomain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.mydomain.domain.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, String>{

}
