//package com.jwt.mainpac.authentification_jwt2.refreshToken;
//
//import com.jwt.mainpac.authentification_jwt2.entity.RefreshToken;
//import com.jwt.mainpac.authentification_jwt2.entity.User;
//import com.jwt.mainpac.authentification_jwt2.exceptions.ExceptionComponent;
//import com.jwt.mainpac.authentification_jwt2.exceptions.TokenAccessException;
//import com.jwt.mainpac.authentification_jwt2.repositories.RefreshRepository;
//import io.jsonwebtoken.ExpiredJwtException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//
//import java.util.Date;
//
//@Component
//public class CheckRefresh {
//
//    @Autowired
//    ExceptionComponent exceptionComponent;
//
//    @Autowired
//    RefreshRepository refreshRepository;
//
//    public boolean checkRef(User user) {
////        try {
//            System.out.println("insideCheckRef");
//            RefreshToken refreshToken = user.getRefreshToken();
//
//            System.out.println(refreshToken.getExpiredDate() + "asdasd");
//
//            return refreshToken.getExpiredDate().before(new Date());
////        }
////        catch (TokenAccessException e){
////            throw new HttpRequestMethodNotSupportedException("aboba");
////        }
//    }
//
//    }
