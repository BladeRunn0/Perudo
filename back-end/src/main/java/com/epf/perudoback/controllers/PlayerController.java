package com.epf.perudoback.controllers;

import com.epf.perudoback.DTO.PlayerDTO;
import com.epf.perudoback.DTO.StudentDto;
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

    @GetMapping("/{id}")
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

    @GetMapping("/{_}")
    public List<Player> playGame(){
        return playerService.playGame();
    }
}
