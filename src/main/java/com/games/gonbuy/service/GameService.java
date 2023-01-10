package com.games.gonbuy.service;

import com.games.gonbuy.entity.Game;

import java.util.List;

public interface GameService {

    Game start(int numberOfPlayers);

    Game getById(long id);

    List<Game> getAll();
}
