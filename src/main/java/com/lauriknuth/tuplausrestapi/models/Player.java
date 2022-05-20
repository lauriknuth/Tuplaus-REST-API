package com.lauriknuth.tuplausrestapi.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance_amount")
    private double balance_amount;

    @Column(name = "winnings_amount")
    private double winnings_amount;

    // Java library Lombok is used to generate getters and setters
}
