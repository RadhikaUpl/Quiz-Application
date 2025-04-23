package com.Quiz_Application_Project.Quiz_Application.Repo;

import com.Quiz_Application_Project.Quiz_Application.Entity.quizScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface quizScoreRepo extends JpaRepository<quizScore,Integer> {

    List<quizScore> findTop5ByOrderByScoreDesc();
}
