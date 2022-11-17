package com.jwt.mainpac.authentification_jwt2.controller;

import com.jwt.mainpac.authentification_jwt2.dto.AuthentificationDTO;
import com.jwt.mainpac.authentification_jwt2.entity.RefreshToken;
import com.jwt.mainpac.authentification_jwt2.entity.User;
import com.jwt.mainpac.authentification_jwt2.filter.FilterProvider;
import com.jwt.mainpac.authentification_jwt2.refreshToken.RefreshCreator;
import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import com.jwt.mainpac.authentification_jwt2.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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
@RequestMapping(value = "/auth/api")
public class AuthController {


    private final AuthenticationManager authenticationManager;

    private final FilterProvider filterProvider;

    private final UserService userService;

    public AuthController(AuthenticationManager auth, FilterProvider provider, UserService service) {
        this.authenticationManager=auth;
        this.filterProvider=provider;
        this.userService = service;
    }

        @Autowired
        UserRepository userRepository;
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    FilterProvider filterProvider;
//
    @Autowired
    RefreshCreator refreshCreator;

    @PostMapping( "/login")
    public ResponseEntity login(@RequestBody AuthentificationDTO authentificationDTO){
        try{
            System.out.println(authentificationDTO.getUsername());
            String username = authentificationDTO.getUsername();
            User user = userRepository.findUserByUserName(username);
            if (user==null){
                throw new UsernameNotFoundException("user with name "+username+" not found");
            }
            refreshCreator.updateRef(user);
            System.out.println(authentificationDTO.getPassword()+" inside authController");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authentificationDTO.getPassword()));

            System.out.println(SecurityContextHolder.getContext().getAuthentication() + " after userService");



            String token = filterProvider.createToken(username, user.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("tokenLogin", token);
            response.put("username", username);
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password - " + e.getMessage()); //невнрные учетные данные
        }
    }
}
