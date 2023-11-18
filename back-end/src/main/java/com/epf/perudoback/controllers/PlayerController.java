package com.epf.perudoback.controllers;

import com.epf.perudoback.DTO.PlayerDTO;
import com.epf.perudoback.DTO.StudentDto;
import com.epf.perudoback.models.Dice;
import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Student;
import com.epf.perudoback.services.PlayerService;
import com.epf.perudoback.services.StudentService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/game/computer-predictions")
    public List<List<String>> computerPredictions(List<Dice> listOfDiceValues){
        return playerService.computerPrediction(listOfDiceValues);
    }

    @GetMapping("/game/applyRules")
    public String applyRules(List<List<String>> predictions, List<Integer> countDices){
        return playerService.applyRules(predictions, countDices);
    }

    @GetMapping("/game/checkDoubt")
    public String checkDoubt(List<String> prevPlayerPred, List<Integer> countDices){
        return playerService.checkDoubt(prevPlayerPred, countDices);
    }

    @GetMapping("/game/playerBet/{betDice}")
    public String playerBet(@PathVariable String betDice, List<Integer> countDices, List<List<String>> predictions){
        return playerService.playerBet(betDice, countDices, predictions);
    }
}
