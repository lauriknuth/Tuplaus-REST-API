package com.lauriknuth.tuplausrestapi.services;

import com.lauriknuth.tuplausrestapi.models.Game;
import com.lauriknuth.tuplausrestapi.models.Player;
import com.lauriknuth.tuplausrestapi.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player player) {
        return playerRepository.save(player);
    }

    public boolean playerExists(long id) {
        return playerRepository.existsById(id);
    }

    public Player findPlayer(long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer.orElseGet(Player::new);
    }

    /**
     * Cashing out player winnings i.e. moving winnings to player balance
     * @param id Player's id
     * @return Player object that represents player data
     */
    public Player cashOutWinnings(long id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        Player player = optionalPlayer.orElseGet(Player::new);
        double winnings_amount = player.getWinnings_amount();
        player.setBalance_amount(player.getBalance_amount()+winnings_amount);
        player.setWinnings_amount(0);
        playerRepository.save(player);
        return player;
    }
}
