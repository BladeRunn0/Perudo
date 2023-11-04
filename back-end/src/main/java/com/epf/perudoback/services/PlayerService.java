package com.epf.perudoback.services;

import com.epf.perudoback.DAO.PlayerDAO;
import com.epf.perudoback.DTO.PlayerDTO;
import com.epf.perudoback.DTO.PlayerMapper;
import com.epf.perudoback.DTO.StudentDto;
import com.epf.perudoback.DTO.StudentMapper;
import com.epf.perudoback.models.Dice;
import com.epf.perudoback.models.DiceValue;
import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerDAO playerDAO;

    public List<Player> findAll() {
        Iterable<Player> it = playerDAO.findAll();
        List <Player> users = new ArrayList<>();
        it.forEach(users::add);
        return users ;
    }

    public Player getById(Long id) {
        return playerDAO.findById(id).orElseThrow();
    }

    @Transactional
    public void deleteById(Long id) {
        playerDAO.deleteById(id);
    }

    @Transactional
    public void addPlayer(PlayerDTO playerDTO) {
        Player player;
        try {
            player = PlayerMapper.fromDTO(playerDTO, null);
        } catch (IOException e) {
            throw new RuntimeException("Error with Player image", e);
        }

        playerDAO.save(player);
    }

    @Transactional
    public void updatePlayer(PlayerDTO playerDTO, Long id) {
        playerDAO.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Student doesn't exist"));
        Player player;
        try {
            player = PlayerMapper.fromDTO(playerDTO, id);
        } catch (IOException e) {
            throw new RuntimeException("Error with Student image", e);
        }
        playerDAO.save(player);
    }

    public List<Player> searchByScore(int scoreId) {
        return playerDAO.findByScoreId(scoreId);
    }


    // -------------------- GAME FUNCTIONS -------------------------

    //Instantiating players
    private  List<Player> createPlayers(int nbPlayer){
        List<Player> listPlayer = new ArrayList<>();
        for(int i=0;  i<nbPlayer;i++){
            listPlayer.add(new Player(5));
        }
        for(int i=0; i<listPlayer.size();i++){
            listPlayer.get(i).rollDice();
        }
        return listPlayer;
    }

    //Checking dice frequencies
    private List<Integer> diceFrequencies(List<Dice> listOfDiceValues){
        List<Integer> countDices = new ArrayList<>(Collections.nCopies(6, 0)); // Initialisation de la liste avec des zéros

        for (Dice dice : listOfDiceValues) {
            if (dice.getDiceValue() != null) {
                int value = dice.getDiceValue().ordinal(); // Obtention de l'indice de l'énumération
                countDices.set(value, countDices.get(value) + 1); // Incrémenter le compteur
            }
        }

        return countDices;
    }

    //Creating computers predictions
    private List<String> computerPrediction(List<Dice> listOfDiceValues, int i){
        enum predict{PACO, DEUX, TROIS, QUATRE, CINQ, SIX, DOUBT} // Without DOUBT
        List<String> prediction = new ArrayList<>();

        //Choose dice's face
        Random randFace = new Random();

        //Choose number
        if(i == 1){ // First player can't doubt
            int randIndexFirstPlayer = randFace.nextInt(predict.values().length-1);

            Random randTotalNumber = new Random();
            int randNumber = randTotalNumber.nextInt(1,listOfDiceValues.size());

            prediction.add(predict.values()[randIndexFirstPlayer].toString());
            prediction.add(String.valueOf(randNumber));
        }else{
            int randIndex = randFace.nextInt(predict.values().length);
            if(predict.values()[randIndex] != predict.DOUBT){
                Random randTotalNumber = new Random();
                int randNumber = randTotalNumber.nextInt(1,listOfDiceValues.size());

                prediction.add(predict.values()[randIndex].toString());
                prediction.add(String.valueOf(randNumber));
            }else{
                prediction.add(predict.values()[randIndex].toString());
            }
        }
        return prediction;
    }

    //Applying game rules to predictions (to be called twice to ensure a good application)
    //Only use the returned string on the second call
    private String applyRules(List<List<String>> predictions, List<Integer> countDices){

        String doubtResult = null;

        for (int i = 1; i < predictions.size(); i++) {
            String currentFace = predictions.get(i).get(0);
            String previousFace = predictions.get(i - 1).get(0);

            if (!currentFace.contains("DOUBT") && !previousFace.contains("DOUBT")) {
                String currentCount = predictions.get(i).get(1);
                String previousCount = predictions.get(i - 1).get(1);
                if (currentFace.equals("PACO") && !previousFace.equals("PACO")) {
                    double divisionResult = (double) Integer.parseInt(previousCount) / 2;
                    int expectedPacoCount = (int) Math.ceil(divisionResult);
                    predictions.get(i).set(1, String.valueOf(expectedPacoCount));
                } else if(currentFace.equals("PACO") && previousFace.equals("PACO")){
                    int updatedCount = Integer.parseInt(previousCount) + 1;
                    predictions.get(i).set(1, String.valueOf(updatedCount));
                }else if (currentFace.equals(previousFace)) {
                    int updatedCount = Integer.parseInt(previousCount) + 1;
                    predictions.get(i).set(1, String.valueOf(updatedCount));
                } else {
                    int currentDiceValue = DiceValue.getStringDiceValue(currentFace);
                    int previousDiceValue = DiceValue.getStringDiceValue(previousFace);
                    if (currentDiceValue <= previousDiceValue) {
                        Random randFace = new Random();
                        if(randFace.nextInt(0,2) == 0){ // On change de face et on garde le nombre
                            currentFace = DiceValue.getStringHigherFace(previousFace);
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

            if (currentFace.contains("DOUBT") && !previousFace.contains("DOUBT")){
                doubtResult = checkDoubt(predictions.get(i-1), countDices);
                break;
            }
        }
        return doubtResult;
    }

    //Checking result of previous player if a doubt appears
    private String checkDoubt(List<String> prevPlayerPred, List<Integer> countDices){

        switch (prevPlayerPred.get(0)){

            case "PACO":
                if(Integer.parseInt(prevPlayerPred.get(1)) == countDices.get(0)){
                    return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was right ! Doubt emitter loses";
                }
                return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was wrong ! Doubt emitter wins";
            case "DEUX":
                if(Integer.parseInt(prevPlayerPred.get(1)) == countDices.get(1)){
                    return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was right ! Doubt emitter loses";
                }
                return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was wrong ! Doubt emitter wins";
            case "TROIS":
                if(Integer.parseInt(prevPlayerPred.get(1)) == countDices.get(2)){
                    return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was right ! Doubt emitter loses";
                }
                return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was wrong ! Doubt emitter wins";
            case "QUATRE":
                if(Integer.parseInt(prevPlayerPred.get(1)) == countDices.get(3)){
                    return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was right ! Doubt emitter loses";
                }
                return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was wrong ! Doubt emitter wins";
            case "CINQ":
                if(Integer.parseInt(prevPlayerPred.get(1)) == countDices.get(4)){
                    return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was right ! Doubt emitter loses";
                }
                return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was wrong ! Doubt emitter wins";
            case "SIX":
                if(Integer.parseInt(prevPlayerPred.get(1)) == countDices.get(5)){
                    return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was right ! Doubt emitter loses";
                }
                return "Prediction " + prevPlayerPred.get(1) + " " + prevPlayerPred.get(0) + " was wrong ! Doubt emitter wins";
            default:
                return null;
        }
    }

    //Managing player bets. TODO - Remove Scanner for front
    private String playerBet(String betDice, List<Integer> countDices, List<List<String>> predictions){
        switch (betDice){
            case "1":
                System.out.println("How many ?");
                String bet = new Scanner(System.in).next();
                if(bet.equals(countDices.get(0).toString())){
                    return "Good job !";
                }else{
                    return "Too bad !";
                }
            case "2":
                System.out.println("How many ?");
                String bet2 = new Scanner(System.in).next();
                if(bet2.equals(countDices.get(1).toString())){
                    return "Good job !";
                }else{
                    return "Too bad !";
                }
            case "3":
                System.out.println("How many ?");
                String bet3 = new Scanner(System.in).next();
                if(bet3.equals(countDices.get(2).toString())){
                    return "Good job !";
                }else{
                    return "Too bad !";
                }
            case "4":
                System.out.println("How many ?");
                String bet4 = new Scanner(System.in).next();
                if(bet4.equals(countDices.get(3).toString())){
                    return "Good job !";
                }else{
                    return "Too bad !";
                }
            case "5":
                System.out.println("How many ?");
                String bet5 = new Scanner(System.in).next();
                if(bet5.equals(countDices.get(4).toString())){
                    return "Good job !";
                }else{
                    return "Too bad !";
                }
            case "6":
                System.out.println("How many ?");
                String bet6 = new Scanner(System.in).next();
                if(bet6.equals(countDices.get(5).toString())){
                    return "Good job !";
                }else{
                    return "Too bad !";
                }
            case "7":
                String doubtResult = checkDoubt(predictions.get(predictions.size()-1), countDices);
                return doubtResult;
            default:
                return "Quitting";
        }
    }


    //Test function for front
    public List<Player> playGame(){
        List<Player> listPlayer = createPlayers(5); //nb en dur de joueurs pour tester le front
        return listPlayer;
    }
}
