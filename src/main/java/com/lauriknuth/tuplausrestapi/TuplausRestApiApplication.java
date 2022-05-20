package com.lauriknuth.tuplausrestapi;

import com.lauriknuth.tuplausrestapi.models.Deck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TuplausRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TuplausRestApiApplication.class, args);
    }

    /**
     * Creates a unique (singleton) instance of card deck that is then used throughout the application
     */
    @Bean(name = "card_deck_1")
    public Deck deckOfCards(){
        return new Deck("card_deck_1");
    }
}
