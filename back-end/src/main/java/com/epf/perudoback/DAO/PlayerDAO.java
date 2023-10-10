package com.epf.perudoback.DAO;

import com.epf.perudoback.models.Player;
import com.epf.perudoback.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface PlayerDAO extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p JOIN p.scores s WHERE s.id= :scoreId ")
    List<Player> findByScoreId(int scoreId);
}
