package com.games.gonbuy.controller;

import com.games.gonbuy.entity.Game;
import com.games.gonbuy.service.GameService;
import com.games.gonbuy.service.RollDiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;
    private RollDiceService rollDiceService;

    @Autowired
    public GameController(GameService gameService, RollDiceService rollDiceService) {
        this.gameService = gameService;
        this.rollDiceService = rollDiceService;
    }

    @GetMapping("/start")
    public ResponseEntity<Game> start(@RequestParam("n") Integer numberOfPlayers) {
        return ResponseEntity.status(200).body(gameService.start(numberOfPlayers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable Long id) {
        return ResponseEntity.status(200).body(gameService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAll() {
        return ResponseEntity.status(200).body(gameService.getAll());
    }

    //change to PostMapping
    @GetMapping("/{id}/move/{playerName}")
    public ResponseEntity<Game> moveTo(@PathVariable Long id, @PathVariable String playerName) {
        Game game = gameService.getById(id);
        game.movePlayer(playerName, rollDiceService.rollDice());
        return ResponseEntity.status(200).body(game);
    }
}
