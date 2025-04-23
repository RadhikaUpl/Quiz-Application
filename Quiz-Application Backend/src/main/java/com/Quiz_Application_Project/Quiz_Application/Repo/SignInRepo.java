package com.Quiz_Application_Project.Quiz_Application.Repo;

import com.Quiz_Application_Project.Quiz_Application.Entity.SIgnInTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignInRepo extends JpaRepository<SIgnInTable,Integer> {

    boolean existsByEmail(String email);
    Optional<SIgnInTable> findByusername(String username);
    boolean existsByUsername(String username);
}
