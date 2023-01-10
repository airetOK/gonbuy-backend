package com.games.gonbuy.service.impl;

import com.games.gonbuy.entity.Game;
import com.games.gonbuy.entity.factory.GameFactory;
import com.games.gonbuy.repository.GameRepository;
import com.games.gonbuy.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "gameService")
public class GameServiceImpl implements GameService {

    private long id;
    private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game start(int numberOfPlayers) {
        if (numberOfPlayers >= 2 && numberOfPlayers <= 6) {
            Game game = GameFactory.newGame(++id, numberOfPlayers);
            gameRepository.add(game);
            return game;
        }
        throw new RuntimeException("Number of players should be from 2 to 6.");
    }

    @Override
    public Game getById(long id) {
        return gameRepository.getById(id);
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.getAll();
    }
}
