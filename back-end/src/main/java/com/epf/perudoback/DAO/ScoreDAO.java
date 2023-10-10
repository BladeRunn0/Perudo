package com.epf.perudoback.DAO;

import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Score;
import com.epf.perudoback.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreDAO extends JpaRepository<Score, Long> {
}
