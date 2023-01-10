package com.games.gonbuy.entity.field;

import com.games.gonbuy.entity.card.Card;
import com.games.gonbuy.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Field {

    private Card card;
    public final int POSITION;
    private List<Player> players;

    public Field(Card card, int POSITION) {
        this.card = card;
        this.POSITION = POSITION;
        players = new ArrayList<>();
    }

    public Card getCard() {
        return card;
    }

    public boolean add(Player player) {
        return players.add(player);
    }

    public Player remove(String name) {
        Player player = players.stream().filter(p -> p.getName().equals(name)).findFirst().orElseThrow();
        if (!players.remove(player)) {
            throw new RuntimeException("Player with '" + name + "' wasn't removed.");
        }
        return player;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
