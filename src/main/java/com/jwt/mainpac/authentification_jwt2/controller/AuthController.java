package com.jwt.mainpac.authentification_jwt2.controller;

import com.jwt.mainpac.authentification_jwt2.dto.AuthentificationDTO;
import com.jwt.mainpac.authentification_jwt2.entity.User;
import com.jwt.mainpac.authentification_jwt2.filter.FilterProvider;
import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import com.jwt.mainpac.authentification_jwt2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    FilterProvider filterProvider;

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AuthentificationDTO authentificationDTO){
        try{
            String email = authentificationDTO.getEmail();

            User user = userRepository.findUserByEmail(email);
            if (user==null){
                throw new UsernameNotFoundException("user with email "+email+" not found");
            }
            String username = user.getUserName();
            System.out.println(authentificationDTO.getPassword()+" inside authController");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authentificationDTO.getPassword()));

            System.out.println(SecurityContextHolder.getContext().getAuthentication() + " after userService");

            String token = filterProvider.createToken(username, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password - " + e.getMessage()); //невнрные учетные данные
        }
    }
}
