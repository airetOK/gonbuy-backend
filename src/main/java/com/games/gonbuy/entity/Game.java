package com.games.gonbuy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.games.gonbuy.entity.card.Card;
import com.games.gonbuy.entity.enums.CardType;
import com.games.gonbuy.entity.factory.CardFactory;
import com.games.gonbuy.entity.factory.FieldFactory;
import com.games.gonbuy.entity.factory.PlayerFactory;
import com.games.gonbuy.entity.field.Field;
import com.games.gonbuy.entity.player.Player;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

    @JsonProperty
    private long id;
    @JsonProperty
    private List<Field> fields = new ArrayList<>();
    @JsonProperty
    private List<Player> players = new ArrayList<>();

    public Game(long id, int numberOfPlayers) {
        this.id = id;
        init(numberOfPlayers);
    }

    public void movePlayer(String name, int number) {
        Field field = getFieldByPlayerName(name);
        Player player = field.remove(name);
        int moveToPos = getMoveToPosition(field.POSITION, number);
        Field moveToField = getFieldByPosition(moveToPos);
        moveToField.add(player);
    }

    private void init(int numberOfPlayers) {
        initPlayers(numberOfPlayers);
        initFields();
        placePlayersToStartPosition();
    }

    private void initPlayers(int countOfPlayers) {
        for (int i = 0; i < countOfPlayers; i++) {
            players.add(PlayerFactory.newPlayer("Player" + (i+1)));
        }
    }

    private void initFields() {
        List<Card> allCards = getAllCards();
        for (int i = 0; i < allCards.size(); i++) {
            fields.add(FieldFactory.newField(allCards.get(i), i));
        }
    }

    private void placePlayersToStartPosition() {
        for (Player player : players) {
            fields.get(0).add(player);
        }
        setFirstPlayerTurn();
    }

    private void setFirstPlayerTurn() {
        players.get(0).setPlayerTurn(true);
    }

    private List<Card> getTypeCards(CardType type) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            cards.add(CardFactory.newCard(type));
        }
        return cards;
    }

    // test-only method
    public int getMoveToPosition(int position, int number) {
        int maxPosition = 27;
        position += number;
        if (position > 27) {
            position = position - maxPosition - 1;
        }
        return position;
    }

    public Field getFieldByPosition(int position) {
        return fields.stream().filter(field -> field.POSITION == position).findFirst().orElseThrow();
    }

    private List<Card> getAllCards() {
        // sequence of adding elements should not be changed
        List<Card> cards = new ArrayList<>();
        cards.add(CardFactory.newCard(CardType.START));
        cards.addAll(getTypeCards(CardType.CLUB));
        cards.addAll(getTypeCards(CardType.FAST_FOOD));
        cards.add(CardFactory.newCard(CardType.PRIZE));
        cards.addAll(getTypeCards(CardType.CLOTHES));
        cards.add(CardFactory.newCard(CardType.PENALTY));
        cards.addAll(getTypeCards(CardType.GYM));
        cards.addAll(getTypeCards(CardType.MARKET));
        cards.add(CardFactory.newCard(CardType.JAIL_OR_PRIZE));
        cards.addAll(getTypeCards(CardType.CAR_SHOP));
        return cards;
    }

    public Field getFieldByPlayerName(String name) {
        Player player = players.stream().filter(p -> p.getName().equals(name)).findFirst().orElseThrow();
        return fields.stream().filter(field -> field.getPlayers().contains(player)).findFirst().orElseThrow();
    }

    public long getId() {
        return id;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
