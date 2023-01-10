package com.games.gonbuy.entity.factory;

import com.games.gonbuy.entity.player.Player;

public class PlayerFactory {

    public static Player newPlayer(String name) {
        return new Player(name);
    }
}
