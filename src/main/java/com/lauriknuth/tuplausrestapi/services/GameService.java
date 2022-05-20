package com.lauriknuth.tuplausrestapi.services;

import com.lauriknuth.tuplausrestapi.models.Card;
import com.lauriknuth.tuplausrestapi.models.Deck;
import com.lauriknuth.tuplausrestapi.models.Game;
import com.lauriknuth.tuplausrestapi.models.Player;
import com.lauriknuth.tuplausrestapi.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerService playerService;


    @Resource(name = "card_deck_1")
    private Deck cardDeck1;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    /**
     * Game logic for playing one round of Tuplaus
     * @param player_id
     * @param bet_amount
     * @param player_chooses_high boolean representing whether player chose to play high or low
     * @return Game object that represents game data
     * (player's bet, player's choice (high or low), dealt card, and whether the player won)
     *
     * NOTE: updates both 'games' and 'players' tables after the game round
     */
    public Game playGame(long player_id,
                    double bet_amount,
                    boolean player_chooses_high){

        // Dealing random card from the deck
        Card dealt_card = cardDeck1.getCard();

        // Checking if player won
        boolean player_is_winner = false;
        if(player_chooses_high&&(dealt_card.getValue()>7)){
            player_is_winner = true;
        }else if((!player_chooses_high)&&(dealt_card.getValue()<7)){
            player_is_winner = true;
        }

        Player player = playerService.findPlayer(player_id);
        double previous_balance = player.getBalance_amount();
        double previous_winnings = player.getWinnings_amount();

        // First deduct the bet from the player's winnings or balance
        if(previous_winnings>=bet_amount){
            previous_winnings=previous_winnings-bet_amount;
        }else {
            previous_balance=previous_balance+previous_winnings-bet_amount;
            previous_winnings=0;
        }

        // Then winnings are either double the bet amount or zero
        double winnings_amount = 0;
        if(player_is_winner){
            winnings_amount = bet_amount*2;
        }else{
            winnings_amount = 0;
        }

        // Finally update winnings and balance amounts
        if(player_is_winner){
            player.setWinnings_amount(previous_winnings+winnings_amount);   // new winnings added
        } else {
            player.setBalance_amount(previous_balance);                     // reduced by bet amount
            player.setWinnings_amount(previous_winnings);
        }

        // Creates a game object to return
        Game game = new Game();
        game.setPlayer(player);
        game.setBet_amount(bet_amount);
        game.setPlayer_chooses_high(player_chooses_high);
        game.setDealt_card(dealt_card.getCardType().getName());
        game.setPlayer_is_winner(player_is_winner);

        // Updates both game and player tables
        gameRepository.save(game);
        playerService.updatePlayer(player);

        return game;
    }
}
