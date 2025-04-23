package com.Quiz_Application_Project.Quiz_Application.Controller;

import com.Quiz_Application_Project.Quiz_Application.Entity.SIgnInTable;
import com.Quiz_Application_Project.Quiz_Application.Service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins="http://127.0.0.1:550")
@RestController
@RequestMapping("/signIn")
public class SignInController
{

    @Autowired
    SignInService signInService;

    @PostMapping("/newuser")
    public String AddNewUser(@ModelAttribute SIgnInTable user , @RequestParam("photo") MultipartFile photo)
    {
        return signInService.addNewUser(user,photo);
    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestBody SIgnInTable user)
    {
        return signInService.login(user);
    }

    @GetMapping("/getDetails/{username}")
    public SIgnInTable getUserDetails(@PathVariable String username)
    {
        return signInService.getUserDeatils(username);
    }
}
