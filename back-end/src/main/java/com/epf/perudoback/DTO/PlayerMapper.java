package com.epf.perudoback.DTO;

import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Student;

import java.io.IOException;

public class PlayerMapper {

    public static Player fromDTO(PlayerDTO dto, Long id) throws IOException {
        return new Player.Builder()
                .id(id)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .scores(dto.getScores())
                .score(dto.getScore())
                .build();
    }

    public static PlayerDTO toDTO (Player player){
        return PlayerDTO.builder()
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .scores(player.getScores())
                .score(player.getScore())
                .build();
    }
}
