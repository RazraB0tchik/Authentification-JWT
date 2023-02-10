package com.jwt.mainpac.authentification_jwt2.controller;

import com.jwt.mainpac.authentification_jwt2.dto.RegistrationDTO;
import com.jwt.mainpac.authentification_jwt2.entity.User;
import com.jwt.mainpac.authentification_jwt2.filter.FilterProvider;
import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import com.jwt.mainpac.authentification_jwt2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/reg")
public class RegistrateController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    FilterProvider filterProvider;

    @PostMapping(value = "/registrationUser")
    public ResponseEntity registration(@Valid @RequestBody RegistrationDTO registrationDTO){
            User user = userRepository.findUserByUserNameOrEmail(registrationDTO.getUsername(), registrationDTO.getEmail());
            if(user!=null){
                return ResponseEntity.badRequest().body("User with email "+registrationDTO.getEmail()+ " or username "+ registrationDTO.getUsername()+" exist.");
            }
            userService.saveNewUser(registrationDTO.getUsername(), registrationDTO.getPassword(), registrationDTO.getEmail());
            String token = filterProvider.createToken(registrationDTO.getUsername(), "USER");
            System.out.println("success token!");
            Map<Object, Object> responseRegistration= new HashMap<>();
            responseRegistration.put("username", registrationDTO.getUsername());
            responseRegistration.put("tokenRegistered", token);
            responseRegistration.put("role", userRepository.findUserByEmail(registrationDTO.getEmail()).getRole());
            return ResponseEntity.ok(responseRegistration);
    }
}
