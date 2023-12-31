package com.epf.perudoback.controllers;

import com.epf.perudoback.DTO.PlayerDTO;
import com.epf.perudoback.DTO.StudentDto;
import com.epf.perudoback.models.Dice;
import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Student;
import com.epf.perudoback.services.PlayerService;
import com.epf.perudoback.services.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("players")
@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    @GetMapping("")
    public List<Player> listPlayers(@RequestParam(required = false) Integer scoreId) {
        if (scoreId != null) {
            return playerService.searchByScore(scoreId);
        }
        return playerService.findAll();
    }

    @GetMapping("/details/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {

        playerService.deleteById(id);
    }

    @PostMapping("")
    public void addPlayer(@RequestBody PlayerDTO playerDTO) {
        playerService.addPlayer(playerDTO);
    }

    @PostMapping("/{id}")
    public void updatePlayer(@RequestBody PlayerDTO playerDTO, @PathVariable Long id) {
        playerService.updatePlayer(playerDTO, id);
    }

    @GetMapping("/game/{nb}")
    public List<Player> createPlayers(@PathVariable Integer nb){
        return playerService.createPlayers(nb);
    }

    @GetMapping("/game/frequencies/{listOfDiceValues}")
    public List<Integer> diceFrequencies(@PathVariable String listOfDiceValues){
        System.out.println(listOfDiceValues);
        return playerService.diceFrequencies(listOfDiceValues);
    }

    @GetMapping("/game/applyRules/{predictionsJSON}/{listOfDiceValues}")
    public List<String> applyRules(@PathVariable String predictionsJSON, @PathVariable String listOfDiceValues){
        List<List<String>> predictions = playerService.getPredictions(predictionsJSON);
        List<Integer> countDices = playerService.diceFrequencies(listOfDiceValues);
        return playerService.applyRules(predictions, countDices);
    }

    @GetMapping("/game/computer-predictions/{listOfDiceValues}/{nb}")
    public List<List<String>> computerPredictions(@PathVariable String listOfDiceValues, @PathVariable int nb){
        return playerService.computerPrediction(listOfDiceValues, nb);
    }

    @GetMapping("/game/playerBet/{betDice}/{listOfDiceValues}/{predictions}")
    public List<String> playerBet(@PathVariable String betDice, @PathVariable String listOfDiceValues, @PathVariable String predictions) {
        return playerService.playerBet(betDice, listOfDiceValues, predictions);
    }
}
