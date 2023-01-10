package com.games.gonbuy.entity.factory;

import com.games.gonbuy.entity.card.*;
import com.games.gonbuy.entity.enums.CardType;


public class CardFactory {

    public static Card newCard(CardType type) {
        switch (type) {
            case GYM: return new GymCard(1,1,2);
            case CAR_SHOP: return new CarShopCard(1,1,2);
            case CLUB: return new ClubCard(1,1,2);
            case MARKET: return new MarketCard(1,1,2);
            case CLOTHES: return new ClothesCard(1,1,2);
            case FAST_FOOD: return new FastFoodCard(1,1,2);
            case START: return new StartCard();
            case PRIZE: return new PrizeCard();
            case JAIL_OR_PRIZE: return new JailOrPrizeCard();
            case PENALTY: return new PenaltyCard();
            default:
                throw new IllegalArgumentException("Unsupported card. You input: " + type.name());
        }
    }
}
