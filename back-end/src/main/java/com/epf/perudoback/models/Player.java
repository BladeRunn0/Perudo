package com.epf.perudoback.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "players")
@Getter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToMany
    @JoinTable(
            name = "player_score",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "score_id"))
    private List<Score> scores;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "score_id")
    private Score score;

    private int activeDiceNumber;
    private List<Dice> dices;

    private Player(Builder builder){
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.scores = builder.scores;
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
        private List<Score> scores;
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

        public Player.Builder scores(List<Score> scores){
            this.scores = scores;
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
            this.dices.add(Dice.getFromNumber(rand.nextInt(7)));
        }
    }

    public void rollDice(int nbDiceToRemove){
        this.dices.clear();
        Random rand = new Random();
        for(int i=0; i < activeDiceNumber-nbDiceToRemove; i++){
            this.dices.add(Dice.getFromNumber(rand.nextInt(7)));
        }
    }

}
