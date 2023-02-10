//package com.jwt.mainpac.authentification_jwt2.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jwt.mainpac.authentification_jwt2.entity.Response;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.HashMap;
//
//@ControllerAdvice
//public class ExceptionController {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity catchValidError(MethodArgumentNotValidException e){
//        HashMap<Object, Object> response = new HashMap<>();
//        response.put("message", e.getMessage());
//        return ResponseEntity.status(400).body(response);
//    }
//
//}
