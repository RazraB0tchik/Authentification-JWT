package com.jwt.mainpac.authentification_jwt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class AuthentificationJwt2Application {

    public static void main(String[] args) {
        SpringApplication.run(AuthentificationJwt2Application.class, args);
    }

}
