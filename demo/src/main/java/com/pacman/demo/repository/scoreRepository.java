package com.pacman.demo.repository;

import java.util.List;

import org.springframework.data.domain.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pacman.demo.entity.score;

public interface scoreRepository extends JpaRepository<score, Integer> {
  // Lấy toàn bộ điểm của 1 user (theo user_id)
    List<Score> findByUserId(Integer userId);

    // Query tùy chỉnh để lấy leaderboard (bàn ở tin trước)
    //@Query("SELECT s.user.username AS username, MAX(s.score) AS score " +
      //     "FROM Score s GROUP BY s.user.username ORDER BY score DESC")
    //List<LeaderboardProjection> getLeaderboard();
}