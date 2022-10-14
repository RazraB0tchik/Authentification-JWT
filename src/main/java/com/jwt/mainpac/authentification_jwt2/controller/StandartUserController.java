package com.jwt.mainpac.authentification_jwt2.controller;


import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/controller1")
public class StandartUserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/getUsers")
    public void getUsers(){
        System.out.println(userRepository.findAllByRole("USER"));
    }

    @GetMapping(value = "/getText")
    public void getText(){
        System.out.println("hello");
    }
}
