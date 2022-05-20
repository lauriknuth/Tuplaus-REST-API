package com.lauriknuth.tuplausrestapi.controllers;

import com.lauriknuth.tuplausrestapi.models.Game;
import com.lauriknuth.tuplausrestapi.models.Player;
import com.lauriknuth.tuplausrestapi.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * Returns all player data records
     * @return List of PLayer objects containing player data for each player
     */
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        List<Player> allPlayers = playerService.getAllPlayers();
        return new ResponseEntity<>(allPlayers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.createPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable long id) {
        if (!playerService.playerExists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Player player = playerService.findPlayer(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    /**
     * Cashing out player winnings i.e. moving winnings to player balance
     * @param id Player's id
     * @return Player object that represents player data
     */
    @PutMapping("/cashout/{id}")
    public ResponseEntity<Player> cashOutWinnings(@PathVariable long id) {
        if (!playerService.playerExists(id)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Player player = playerService.cashOutWinnings(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
