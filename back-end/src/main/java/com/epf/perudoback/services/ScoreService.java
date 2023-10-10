package com.epf.perudoback.services;


import com.epf.perudoback.DAO.MajorDao;
import com.epf.perudoback.DAO.ScoreDAO;
import com.epf.perudoback.models.Major;
import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Score;
import com.epf.perudoback.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreDAO scoreDao;
    public List<Score> findAll() {
        Iterable<Score> it = scoreDao.findAll();
        List <Score> scores = new ArrayList<>();
        it.forEach(scores::add);
        return scores;
    }

}
