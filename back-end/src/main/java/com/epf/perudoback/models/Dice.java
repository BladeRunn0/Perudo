package com.epf.perudoback.models;

import jakarta.persistence.*;

@Entity(name = "dices")
public class Dice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiceValue diceValue;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Dice(){}

    public DiceValue getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(DiceValue diceValue) {
        this.diceValue = diceValue;
    }

    @Override
    public String toString() {
        return diceValue.toString();
    }
}
