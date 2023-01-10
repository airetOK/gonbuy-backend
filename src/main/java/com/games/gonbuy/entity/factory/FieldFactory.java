package com.games.gonbuy.entity.factory;

import com.games.gonbuy.entity.card.Card;
import com.games.gonbuy.entity.field.Field;
import com.games.gonbuy.entity.field.GameField;

public class FieldFactory {

    public static Field newField(Card card, int position) {
        return new GameField(card, position);
    }
}
