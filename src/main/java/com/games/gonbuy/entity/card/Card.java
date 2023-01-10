package com.games.gonbuy.entity.card;

import com.games.gonbuy.entity.player.Player;

public abstract class Card {

    public final int PRICE;
    public final int REVENUE;
    public final int RENTAL_PRICE;
    private Player owner;

    public Card() {
        this.PRICE = 0;
        this.REVENUE = 0;
        this.RENTAL_PRICE = 0;
    }

    public Card(int PRICE, int REVENUE, int RENTAL_PRICE) {
        this.PRICE = PRICE;
        this.REVENUE = REVENUE;
        this.RENTAL_PRICE = RENTAL_PRICE;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }
}
