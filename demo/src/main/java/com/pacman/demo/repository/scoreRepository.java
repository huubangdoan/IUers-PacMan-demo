package com.pacman.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pacman.demo.entity.score; // Chỉ import entity của bạn[cite: 2]

public interface scoreRepository extends JpaRepository<score, Integer> {
    // Sửa kiểu trả về thành List<score> khớp với tên entity
    List<score> findByUserId(Integer userId);
    // Query tùy chỉnh để lấy leaderboard (bàn ở tin trước)
    //@Query("SELECT s.user.username AS username, MAX(s.score) AS score " +
      //     "FROM Score s GROUP BY s.user.username ORDER BY score DESC")
    //List<LeaderboardProjection> getLeaderboard();
}