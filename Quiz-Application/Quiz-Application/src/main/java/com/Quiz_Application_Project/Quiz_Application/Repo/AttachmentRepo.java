package com.Quiz_Application_Project.Quiz_Application.Repo;

import com.Quiz_Application_Project.Quiz_Application.Entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepo extends JpaRepository<Attachment,Integer> {

    public Attachment findByQuizType(String quizType);
}
