package com.Quiz_Application_Project.Quiz_Application.Controller;

import com.Quiz_Application_Project.Quiz_Application.Entity.Questions;
import com.Quiz_Application_Project.Quiz_Application.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://127.0.0.1:5501")
@RestController
@RequestMapping("/questions")
public class QuestionController
{
    @Autowired
   QuestionService que;

    @PostMapping("/add")
    public String addQue(@RequestBody Questions questions)
    {
        return que.addQue(questions);
    }

    @GetMapping("/{queType}")
    public List<Questions> getAllQuestionsByType(@PathVariable String queType)
    {
        return que.getAllQuestions(queType);
    }

    @DeleteMapping("/{id}")
    public String DeleteQuestionById(@PathVariable int id)
    {
        return que.deletebyId(id);
    }
    
}
