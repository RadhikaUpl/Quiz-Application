package com.Quiz_Application_Project.Quiz_Application.Repo;

import com.Quiz_Application_Project.Quiz_Application.Entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepo extends JpaRepository<Options,Integer>
{

}
