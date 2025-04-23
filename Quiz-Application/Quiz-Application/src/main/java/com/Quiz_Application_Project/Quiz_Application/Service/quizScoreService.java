package com.Quiz_Application_Project.Quiz_Application.Service;

import com.Quiz_Application_Project.Quiz_Application.Entity.quizScore;
import com.Quiz_Application_Project.Quiz_Application.Repo.quizScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class quizScoreService {

    @Autowired
    quizScoreRepo quizscorerepo;
    public String addData(quizScore qs)
    {
        quizscorerepo.save(qs);
        return "saved successully";
    }

    public List<quizScore> getListOfTopPerformers()
    {
        return quizscorerepo.findTop5ByOrderByScoreDesc();
    }
}
