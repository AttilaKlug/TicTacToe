package hu.mydomain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hu.mydomain.domain.Game;
import hu.mydomain.repositories.GameRepository;

@Repository
public class AllRepositoryImpl {

    @Autowired
    private GameRepository gameRepository;
    
    public List<Game> findAllGame(){
        List<Game> games = new ArrayList<>();
        gameRepository.findAll().forEach(games::add);;
        return games;
    }
    
    public long countGame(){
        return gameRepository.count();
    }
    
    public Game saveGame(Game game){
        return gameRepository.save(game);
    }
}
