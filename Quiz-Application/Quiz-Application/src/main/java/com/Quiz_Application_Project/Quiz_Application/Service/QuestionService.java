package com.Quiz_Application_Project.Quiz_Application.Service;

import com.Quiz_Application_Project.Quiz_Application.Entity.Options;
import com.Quiz_Application_Project.Quiz_Application.Entity.Questions;
import com.Quiz_Application_Project.Quiz_Application.Repo.OptionRepo;
import com.Quiz_Application_Project.Quiz_Application.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpPlus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService
{
    @Autowired
    QuestionRepo repo;

    @Autowired
    OptionRepo optRepo;


    public String addQue(Questions questions)
    {
        if(questions.getOptions()!=null)
        {
        for(Options options:questions.getOptions())
        {
            options.setQuestions(questions);

        }
        }
        repo.save(questions);

        return "Questions Added";
    }

    public List<Questions> getAllQuestions(String type)
    {
      return repo.findByQueType(type);
    }
    public String deletebyId(int id)
    {
        Optional<Questions> byId = repo.findById(id);
       if( byId.isPresent())
       {
           repo.deleteById(id);
           return "deleted";
       }
       return "id not found";
    }



}
