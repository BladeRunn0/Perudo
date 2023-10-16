package com.epf.perudoback;

import com.epf.perudoback.models.Dice;
import com.epf.perudoback.models.Player;

import java.util.*;

public class Main {

    private static List<Player> createPlayers(int nbPlayer){
        List<Player> listPlayer = new ArrayList<>();
        for(int i=0;  i<nbPlayer;i++){
            listPlayer.add(new Player(5));
        }
        for(int i=0; i<listPlayer.size();i++){
            listPlayer.get(i).rollDice();
        }
        return listPlayer;
    }
    private static List<Integer> diceFrequencies(List<Dice> listOfDices){
        List<Integer> countDices = new ArrayList<>();
        countDices.add(Collections.frequency(listOfDices, Dice.PACO));
        countDices.add(Collections.frequency(listOfDices, Dice.DEUX));
        countDices.add(Collections.frequency(listOfDices, Dice.TROIS));
        countDices.add(Collections.frequency(listOfDices, Dice.QUATRE));
        countDices.add(Collections.frequency(listOfDices, Dice.CINQ));
        countDices.add(Collections.frequency(listOfDices, Dice.SIX));

        return countDices;
    }

    private static List<String> computerPrediction(List<Dice> listOfDices){
        enum predict{PACO, DEUX, TROIS, QUATRE, CINQ, SIX} // Without DOUBT
        List<String> prediction = new ArrayList<>();

        //Choose dice's face
        Random randFace = new Random();
        int randIndex = randFace.nextInt(predict.values().length);

        //Choose number
//        if(predict.values()[randIndex] != predict.DOUBT){
//            Random randTotalNumber = new Random();
//            int randNumber = randTotalNumber.nextInt(listOfDices.size());
//
//            prediction.add(predict.values()[randIndex].toString());
//            prediction.add(String.valueOf(randNumber));
//        }else{
//            prediction.add(predict.values()[randIndex].toString());
//        }
        Random randTotalNumber = new Random();
        int randNumber = randTotalNumber.nextInt(listOfDices.size());
        prediction.add(predict.values()[randIndex].toString());
        prediction.add(String.valueOf(randNumber));

        return prediction;

    }

    private static void applyRules(List<List<String>> predictions){
        for (int i = 1; i < predictions.size(); i++) {
            String currentFace = predictions.get(i).get(0);
            String previousFace = predictions.get(i - 1).get(0);

            if (!currentFace.equals("DOUBT")) {
                String currentCount = predictions.get(i).get(1);
                String previousCount = predictions.get(i - 1).get(1);
                if (currentFace.equals("PACO") && !previousFace.equals("PACO")) {
                    double divisionResult = (double) Integer.parseInt(previousCount) / 2;
                    int expectedPacoCount = (int) Math.ceil(divisionResult);
                    predictions.get(i).set(1, String.valueOf(expectedPacoCount));
                } else if(currentFace.equals("PACO") && previousFace.equals("PACO")){
                    if (Integer.parseInt(currentCount) <= Integer.parseInt(previousCount)) {
                        int updatedCount = Integer.parseInt(previousCount) + 1;
                        predictions.get(i).set(1, String.valueOf(updatedCount));
                    }
                }else if (currentFace.equals(previousFace)) {
                    if (Integer.parseInt(currentCount) <= Integer.parseInt(previousCount)) {
                        int updatedCount = Integer.parseInt(previousCount) + 1;
                        predictions.get(i).set(1, String.valueOf(updatedCount));
                    }
                } else {
                    int currentDiceValue = Dice.getStringDiceValue(currentFace);
                    int previousDiceValue = Dice.getStringDiceValue(previousFace);
                    if (currentDiceValue <= previousDiceValue) {
                        Random randFace = new Random();
                        if(randFace.nextInt(0,2) == 0){ // On change de face et on garde le nombre
                            currentFace = Dice.getStringHigherFace(previousFace);
                            predictions.get(i).set(0, currentFace);
                        }else{  // On garde la face précédente
                            currentFace = previousFace;
                            predictions.get(i).set(0, currentFace);
                            if (Integer.parseInt(currentCount) <= Integer.parseInt(previousCount)) {
                                Random randCount = new Random();
                                int updatedCount = randCount.nextInt(Integer.parseInt(previousCount)+1, Integer.parseInt(previousCount)+3);
                                predictions.get(i).set(1, String.valueOf(updatedCount));
                            }
                        }
                    }else{
                        if(previousFace.equals("PACO")){
                            int updateCount = Integer.parseInt(previousCount) * 2 + 1;
                            predictions.get(i).set(1, String.valueOf(updateCount));
                        }else{
                            predictions.get(i).set(1, String.valueOf(previousCount));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        boolean isPlaying = true;

        System.out.println("Choose between 2 and 6 players :\n");
        Scanner scNbPlayer = new Scanner(System.in);
        int nbPlayer = scNbPlayer.nextInt();

        // Initiating list of players
        List<Player> listPlayer = createPlayers(nbPlayer);

        // Showing dices of human player
        System.out.println("Your dices : " + listPlayer.get(0).getDices());

        // Instantiating global list of all dices on table to manage predictions
        List<Dice> listOfDices = new ArrayList<>();
        for(int i=0; i<listPlayer.size();i++){
            listOfDices.addAll(listPlayer.get(i).getDices());
        }
        System.out.println("Concat : " + listOfDices);

        // Computing frequency of each face of dice
        List<Integer> countDices = diceFrequencies(listOfDices);

        // Making predictions
        List<List<String>> predictions = new ArrayList<>();
        for(int i = 1; i<listPlayer.size(); i++){
            predictions.add(computerPrediction(listOfDices));
        }
        System.out.println("Les prédictions de base :\n" + predictions);

        // Applying rules on computer predictions - TODO gestion doute
        applyRules(predictions);
        applyRules(predictions);


        System.out.println("Prédictions modifiées :\n" + predictions);

//        while(isPlaying){
//            System.out.println("Place your bets :\n" +
//                    "1 - PACO\n" +
//                    "2 - DEUX\n" +
//                    "3 - TROIS\n" +
//                    "4 - QUATRE\n" +
//                    "5 - CINQ\n" +
//                    "6 - SIX\n" +
//                    "0 - Quit game\n");
//            Scanner scPlaceBet = new Scanner(System.in);
//            String betDice = scPlaceBet.next();
//            switch (betDice){
//                case "1":
//                    System.out.println("How many ?");
//                    String bet = new Scanner(System.in).next();
//                    if(bet.equals(countDices.get(0).toString())){
//                        System.out.println("Good job !");
//                        break;
//                    }else{
//                        System.out.println("Too bad !");
//                        break;
//                    }
//                case "2":
//                    System.out.println("How many ?");
//                    String bet2 = new Scanner(System.in).next();
//                    if(bet2.equals(countDices.get(1).toString())){
//                        System.out.println("Good job !");
//                        break;
//                    }else{
//                        System.out.println("Too bad !");
//                        break;
//                    }
//                case "3":
//                    System.out.println("How many ?");
//                    String bet3 = new Scanner(System.in).next();
//                    if(bet3.equals(countDices.get(2).toString())){
//                        System.out.println("Good job !");
//                        break;
//                    }else{
//                        System.out.println("Too bad !");
//                        break;
//                    }
//                case "4":
//                    System.out.println("How many ?");
//                    String bet4 = new Scanner(System.in).next();
//                    if(bet4.equals(countDices.get(3).toString())){
//                        System.out.println("Good job !");
//                        break;
//                    }else{
//                        System.out.println("Too bad !");
//                        break;
//                    }
//                case "5":
//                    System.out.println("How many ?");
//                    String bet5 = new Scanner(System.in).next();
//                    if(bet5.equals(countDices.get(4).toString())){
//                        System.out.println("Good job !");
//                        break;
//                    }else{
//                        System.out.println("Too bad !");
//                        break;
//                    }
//                case "6":
//                    System.out.println("How many ?");
//                    String bet6 = new Scanner(System.in).next();
//                    if(bet6.equals(countDices.get(5).toString())){
//                        System.out.println("Good job !");
//                        break;
//                    }else{
//                        System.out.println("Too bad !");
//                        break;
//                    }
//                default:
//                    isPlaying = false;
//                    break;
//            }
//        }
    }
}
