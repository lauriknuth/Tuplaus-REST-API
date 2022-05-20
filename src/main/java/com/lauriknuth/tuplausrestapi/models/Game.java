package com.lauriknuth.tuplausrestapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private Timestamp creation_date;
    @UpdateTimestamp
    private Timestamp update_date;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    @JsonGetter("player")
    public String player() {
        if(player != null){
            return "/api/players/" + player.getId();
        } else {
            return null;
        }
    }

    @Column(name = "bet_amount")
    private double bet_amount;

    @Column(name = "player_chooses_high")
    private boolean player_chooses_high;

    @Column(name = "dealt_card")
    private String dealt_card;

    @Column(name = "player_is_winner")
    private boolean player_is_winner;

}
