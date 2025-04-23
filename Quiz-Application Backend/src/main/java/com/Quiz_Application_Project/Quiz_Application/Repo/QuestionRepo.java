package com.Quiz_Application_Project.Quiz_Application.Repo;

import com.Quiz_Application_Project.Quiz_Application.Entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Questions,Integer>
{


   List<Questions> findByQueType(String queType);
}
