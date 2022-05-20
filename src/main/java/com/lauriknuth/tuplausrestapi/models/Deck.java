package com.lauriknuth.tuplausrestapi.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public final class Deck {

    private String name;
    private final Stack<Card> cards;

    /**
     * Constructor that creates a deck of 52 cards and shuffles it in random order
     * @param name Name of the deck
     */
    public Deck(String name) {
        this.name = name;
        this.cards = new Stack<>();
        Arrays.stream(CardType.values()).forEach(card -> cards.add(new Card(card)));
        Collections.shuffle(cards);
    }

    /**
     * Getter that pops the top card from the card deck (represented by stack)
     * @return
     */
    public Card getCard() {
        return this.cards.pop();
    }

    public String getName() {
        return this.name;
    }
}
