package com.Quiz_Application_Project.Quiz_Application.Controller;

import com.Quiz_Application_Project.Quiz_Application.Entity.quizScore;
import com.Quiz_Application_Project.Quiz_Application.Service.quizScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://127.0.0.1:5501")
@RestController
@RequestMapping("/score")
public class QuizScoreController {

    @Autowired
    quizScoreService quizscoreservice;
    @PostMapping("/addData")
    public String addData(@RequestBody quizScore quizscore)
    {
        return quizscoreservice.addData(quizscore);
    }

    @GetMapping("/getTopper")
    public List<quizScore> getTop10()
    {
        return quizscoreservice.getListOfTopPerformers();
    }
}
