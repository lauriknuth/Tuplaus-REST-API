package com.lauriknuth.tuplausrestapi.controllers;

import com.lauriknuth.tuplausrestapi.models.Game;
import com.lauriknuth.tuplausrestapi.services.GameService;
import com.lauriknuth.tuplausrestapi.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    /**
     * Returns all game data records
     * @return List of Game objects containing game data from each round of the game
     */
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames(){
        List<Game> allGames = gameService.getAllGames();
        return new ResponseEntity<>(allGames,HttpStatus.OK);
    }

    /**
     * Plays one round of Tuplaus
     * @param player_id
     * @param bet_amount
     * @param player_chooses_high boolean representing whether player chose to play high or low
     * @return Game object that contains game data
     * (player's bet, player's choice (high or low), dealt card, and whether the player won)
     *
     * NOTE: Returns CONFLICT if player_id does not exist or if player has insufficient balance
     * NOTE: updates both 'games' and 'players' tables after the game round
     */
    @PutMapping("/{player_id}/{bet_amount}/{player_chooses_high}")
    public ResponseEntity<Game> playGame(@PathVariable long player_id,
                                         @PathVariable double bet_amount,
                                         @PathVariable boolean player_chooses_high) {
        if (!playerService.playerExists(player_id)) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        } else if ((playerService.findPlayer(player_id).getBalance_amount()
                +playerService.findPlayer(player_id).getWinnings_amount())<bet_amount) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Game game = gameService.playGame(player_id,bet_amount,player_chooses_high);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }
}
