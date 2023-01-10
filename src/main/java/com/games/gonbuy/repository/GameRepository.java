package com.games.gonbuy.repository;

import com.games.gonbuy.entity.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameRepository {

    private List<Game> games = new ArrayList<>();

    public boolean add(Game game) {
        return games.add(game);
    }

    public Game getById(long id) {
        return games.stream().filter(game -> game.getId() == id).findFirst().orElseThrow();
    }

    public List<Game> getAll() {
        return games;
    }
}
