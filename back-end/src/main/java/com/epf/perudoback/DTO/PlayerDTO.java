package com.epf.perudoback.DTO;

import com.epf.perudoback.models.Score;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
public class PlayerDTO {
    private String firstName;
    private String lastName;
    private List<Score> scores;
    private Score score;
}
