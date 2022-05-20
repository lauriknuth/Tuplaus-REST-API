package com.lauriknuth.tuplausrestapi.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

public final class Card implements Serializable {

    @Enumerated(EnumType.STRING)
    private final CardType cardType;

    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getValue() {
        return cardType.getValue();
    }
}
