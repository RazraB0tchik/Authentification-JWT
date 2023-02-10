package com.jwt.mainpac.authentification_jwt2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.mainpac.authentification_jwt2.dto.UpdateAccessDTO;
import com.jwt.mainpac.authentification_jwt2.entity.User;
import com.jwt.mainpac.authentification_jwt2.filter.FilterProvider;
import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/update")
public class UpdateAccess {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FilterProvider filterProvider;

    @PostMapping(value ="/getAccess")
    public ResponseEntity updateAccess(@RequestBody UpdateAccessDTO updateAccessDTO) throws JsonProcessingException {
//        if((updateAccessDTO.getUsername()==null)||(updateAccessDTO.getRole()==null)){
//            ObjectMapper mapper = new ObjectMapper();
//            return ResponseEntity.status(400).body(mapper.writeValueAsString("invalid enter parameters"));
//        }
        System.out.println("im here");
        System.out.println(updateAccessDTO.getUsername() + " " + updateAccessDTO.getRole());
        String token = filterProvider.createToken(updateAccessDTO.getUsername(), updateAccessDTO.getRole());
        HashMap<Object, Object> newAccess = new HashMap<>();
        newAccess.put("tokenUpdate", token);
        newAccess.put("username", updateAccessDTO.getUsername());
        newAccess.put("role", updateAccessDTO.getRole());

        return ResponseEntity.ok(newAccess);
    }
}
