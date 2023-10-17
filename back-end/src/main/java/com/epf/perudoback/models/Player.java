package com.epf.perudoback.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "players")
@Getter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "score_id")
    private Score score;

    @Column(name = "active_dice_number")
    private Integer activeDiceNumber;
    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    private List<Dice> dices;

    private Player(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.score = builder.score;
    }

    public Player(int activeDiceNumber) {
        this.dices = new ArrayList<Dice>();
        this.activeDiceNumber = activeDiceNumber;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private Score score;

        public Player.Builder id (Long id) {
            this.id = id;
            return this;
        }

        public Player.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Player.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Player.Builder score(Score score) {
            this.score = score;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }

    public void rollDice(){
        this.dices.clear();
        Random rand = new Random();
        for(int i=0; i < activeDiceNumber; i++){
            Dice dice = new Dice();
            dice.setDiceValue(DiceValue.getFromNumber(rand.nextInt(7)));
            this.dices.add(dice);

        }
    }

    public void rollDice(int nbDiceToRemove){
        this.dices.clear();
        Random rand = new Random();
        for(int i=0; i < activeDiceNumber-nbDiceToRemove; i++){
            Dice dice = new Dice();
            dice.setDiceValue(DiceValue.getFromNumber(rand.nextInt(7)));
            this.dices.add(dice);
        }
    }

}
