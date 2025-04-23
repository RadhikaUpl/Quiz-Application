package com.Quiz_Application_Project.Quiz_Application.Service;

import com.Quiz_Application_Project.Quiz_Application.Entity.SIgnInTable;
import com.Quiz_Application_Project.Quiz_Application.Repo.SignInRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class SignInService {

    @Autowired
    SignInRepo signInRepo;

    //Generating file URL
    public String saveFile(MultipartFile file)
    {
        try
        {
            //defining the path of the folder
            Path uploadPath= Paths.get("uploads/");
            //if path not exists then create directory
            if(!Files.exists(uploadPath))
            {
                Files.createDirectories(uploadPath);
            }

            //generating unique name for file
            String fileName=System.currentTimeMillis() + "_" +file.getOriginalFilename();

            //saving the file in upload folder
            Path filePath=uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

            //generating url
            String fileUrl="http://localhost:8080/uploads/"+fileName;

            return fileUrl;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String addNewUser(SIgnInTable user,MultipartFile photo)
    {
        if(signInRepo.existsByUsername(user.getUsername()))
        {
            return "username already taken";
        }
        if (signInRepo.existsByEmail(user.getEmail())) {
            return "false";
        }
        String photoUrl=saveFile(photo);
        SIgnInTable sIgnInTable=new SIgnInTable(user.getEmail(),user.getUsername(),user.getPassword(),photoUrl);
        signInRepo.save(sIgnInTable);
            return "true";

    }
    public String login(SIgnInTable user)
    {
        Optional<SIgnInTable> repoUser = signInRepo.findByusername(user.getUsername());
        if(repoUser.isPresent())
        {
            SIgnInTable sIgnInobj = repoUser.get();
            if(user.getPassword().equals(sIgnInobj.getPassword()))
            {
                return "true";
            }

        }
        return "false";
    }
    public SIgnInTable getUserDeatils(String username)
    {
        Optional<SIgnInTable> byusername = signInRepo.findByusername(username);
        if(byusername.isPresent())
        {
            SIgnInTable sIgnInTable = byusername.get();
            return sIgnInTable;
        }
        return null;
    }
}
