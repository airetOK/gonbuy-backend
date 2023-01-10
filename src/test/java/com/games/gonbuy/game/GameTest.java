package com.games.gonbuy.game;

import com.games.gonbuy.entity.Game;
import com.games.gonbuy.entity.card.*;
import com.games.gonbuy.entity.field.Field;
import static org.assertj.core.api.Assertions.*;

import com.games.gonbuy.service.GameService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameTest {

    private GameService gameService;
    private Game game;
    private Game game2;

    @Autowired
    public GameTest(GameService gameService) {
        this.gameService = gameService;
    }

    @BeforeAll
    void init() {
        gameService.start(4);
        gameService.start(2);
        game = gameService.getById(1);
        game2 = gameService.getById(2);
    }

    @Test
    void testInitPlayers() {
        assertThat(game.getPlayers().size()).isEqualTo(4);
    }

    @Test
    void testGetAllGames() {
        assertThat(gameService.getAll().size()).isEqualTo(2);
    }

    @Test
    void testGetGameByIdException() {
        assertThatThrownBy(() -> gameService.getById(3)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void testInitFields() {
        List<Field> fields = game.getFields();
        assertThat(fields.size()).isEqualTo(28);
        assertThat(fields.get(0).getCard().getClass()).isEqualTo(StartCard.class);
        assertThat(fields.get(9).getCard().getClass()).isEqualTo(PrizeCard.class);
        assertThat(fields.get(14).getCard().getClass()).isEqualTo(PenaltyCard.class);
        assertThat(fields.get(16).getCard().getClass()).isEqualTo(GymCard.class);
        assertThat(fields.get(21).getCard().getClass()).isEqualTo(MarketCard.class);
        assertThat(fields.get(23).getCard().getClass()).isEqualTo(JailOrPrizeCard.class);
        assertThat(fields.get(27).getCard().getClass()).isEqualTo(CarShopCard.class);
        assertThatThrownBy(() -> fields.get(28)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void testStartField() {
        List<Field> fields = game.getFields();
        assertThat(fields.get(0).POSITION).isEqualTo(0);
        assertThat(fields.get(0).getPlayers().size()).isEqualTo(4);
    }

    @Test
    void testFirstPlayerTurnOnFirstPosition() {
        List<Field> fields = game.getFields();
        assertThat(fields.get(0).getPlayers().get(0).isPlayerTurn()).isEqualTo(true);
        assertThat(fields.get(0).getPlayers().get(1).isPlayerTurn()).isEqualTo(false);
        assertThat(fields.get(0).getPlayers().get(2).isPlayerTurn()).isEqualTo(false);
        assertThat(fields.get(0).getPlayers().get(3).isPlayerTurn()).isEqualTo(false);
    }

    @Test
    void testMovePlayer() {
        game.movePlayer("Player2", 5);
        assertThat(game.getFieldByPosition(0).getPlayers().size()).isEqualTo(3);
        assertThat(game.getFieldByPosition(0).getPlayers().get(1).getName()).isEqualTo("Player3");
        assertThat(game.getFieldByPosition(5).getPlayers().size()).isEqualTo(1);
        assertThat(game.getFieldByPosition(5).getPlayers().get(0).getName()).isEqualTo("Player2");

        game.movePlayer("Player2", 6);
        assertThat(game.getFieldByPosition(5).getPlayers().size()).isEqualTo(0);
        assertThat(game.getFieldByPosition(11).getPlayers().get(0).getName()).isEqualTo("Player2");

        game.movePlayer("Player2", 6);
        assertThat(game.getFieldByPosition(11).getPlayers().size()).isEqualTo(0);
        assertThat(game.getFieldByPosition(17).getPlayers().get(0).getName()).isEqualTo("Player2");

        game.movePlayer("Player2", 6);
        assertThat(game.getFieldByPosition(17).getPlayers().size()).isEqualTo(0);
        assertThat(game.getFieldByPosition(23).getPlayers().get(0).getName()).isEqualTo("Player2");

        game.movePlayer("Player2", 5);
        assertThat(game.getFieldByPosition(23).getPlayers().size()).isEqualTo(0);
        assertThat(game.getFieldByPosition(0).getPlayers().get(3).getName()).isEqualTo("Player2");
        assertThat(game.getFieldByPosition(0).getPlayers().size()).isEqualTo(4);
    }

    @Test
    void testGetPosition() {
        assertThat(game.getMoveToPosition(6, 5)).isEqualTo(11);
        assertThat(game.getMoveToPosition(0, 4)).isEqualTo(4);
        assertThat(game.getMoveToPosition(21, 6)).isEqualTo(27);
        assertThat(game.getMoveToPosition(27, 2)).isEqualTo(1);
        assertThat(game.getMoveToPosition(24, 6)).isEqualTo(2);
        assertThat(game.getMoveToPosition(27, 1)).isEqualTo(0);
        assertThat(game.getMoveToPosition(20, 12)).isEqualTo(4);
    }

}
