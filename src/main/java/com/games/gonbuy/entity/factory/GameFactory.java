package com.games.gonbuy.entity.factory;

import com.games.gonbuy.entity.Game;

public class GameFactory {

    public static Game newGame(long id, int numberOfPlayers) {
        return new Game(id, numberOfPlayers);
    }
}
