package com.epf.perudoback.services;

import com.epf.perudoback.DAO.PlayerDAO;
import com.epf.perudoback.DTO.PlayerDTO;
import com.epf.perudoback.DTO.PlayerMapper;
import com.epf.perudoback.DTO.StudentDto;
import com.epf.perudoback.DTO.StudentMapper;
import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public List<Player> playGame(){
        List<Player> listPlayer = createPlayers(5); //nb en dur de joueurs pour tester le front
        return listPlayer;
    }
}
