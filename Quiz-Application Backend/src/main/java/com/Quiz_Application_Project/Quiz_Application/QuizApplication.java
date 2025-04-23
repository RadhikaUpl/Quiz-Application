package com.Quiz_Application_Project.Quiz_Application;

import com.Quiz_Application_Project.Quiz_Application.Controller.QuestionController;
import com.Quiz_Application_Project.Quiz_Application.Entity.Questions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuizApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(QuizApplication.class, args);


	}

}
