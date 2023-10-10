package com.epf.perudoback;

import com.epf.perudoback.models.Dice;
import com.epf.perudoback.models.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Player player = new Player(5);
        player.rollDice();
        System.out.println(player.getDices());

        Player player1 = new Player(5);
        player1.rollDice();
        System.out.println(player1.getDices());

        List<Dice> playerDice = player.getDices();
        List<Dice> player1Dice = player1.getDices();

        ArrayList<Dice> listDices = new ArrayList<>(playerDice);
        listDices.addAll(player1Dice);

        System.out.println("Concat " + listDices);

        List<Integer> countDices = new ArrayList<>();
        countDices.add(Collections.frequency(listDices, Dice.PACO));
        countDices.add(Collections.frequency(listDices, Dice.DEUX));
        countDices.add(Collections.frequency(listDices, Dice.TROIS));
        countDices.add(Collections.frequency(listDices, Dice.QUATRE));
        countDices.add(Collections.frequency(listDices, Dice.CINQ));
        countDices.add(Collections.frequency(listDices, Dice.SIX));

        System.out.println(countDices);


        System.out.println("Place your bets :\n" +
                "1 - PACO\n" +
                "2 - DEUX\n" +
                "3 - TROIS\n" +
                "4 - QUATRE\n" +
                "5 - CINQ\n" +
                "6 - SIX\n");
        Scanner sc = new Scanner(System.in);
        String betDice = sc.next();
        switch (betDice){
            case "1":
                System.out.println("How many ?");
                String bet = new Scanner(System.in).next();
                if(bet.equals(countDices.get(0).toString())){
                    System.out.println("Good job !");
                    break;
                }else{
                    System.out.println("Too bad !");
                    break;
                }
            case "2":
                System.out.println("How many ?");
                String bet2 = new Scanner(System.in).next();
                if(bet2.equals(countDices.get(1).toString())){
                    System.out.println("Good job !");
                    break;
                }else{
                    System.out.println("Too bad !");
                    break;
                }
            case "3":
                System.out.println("How many ?");
                String bet3 = new Scanner(System.in).next();
                if(bet3.equals(countDices.get(2).toString())){
                    System.out.println("Good job !");
                    break;
                }else{
                    System.out.println("Too bad !");
                    break;
                }
            case "4":
                System.out.println("How many ?");
                String bet4 = new Scanner(System.in).next();
                if(bet4.equals(countDices.get(3).toString())){
                    System.out.println("Good job !");
                    break;
                }else{
                    System.out.println("Too bad !");
                    break;
                }
            case "5":
                System.out.println("How many ?");
                String bet5 = new Scanner(System.in).next();
                if(bet5.equals(countDices.get(4).toString())){
                    System.out.println("Good job !");
                    break;
                }else{
                    System.out.println("Too bad !");
                    break;
                }
            case "6":
                System.out.println("How many ?");
                String bet6 = new Scanner(System.in).next();
                if(bet6.equals(countDices.get(5).toString())){
                    System.out.println("Good job !");
                    break;
                }else{
                    System.out.println("Too bad !");
                    break;
                }
        }

    }
}
