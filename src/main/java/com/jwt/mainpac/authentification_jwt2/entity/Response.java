package com.jwt.mainpac.authentification_jwt2.entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;

//@Component
public class Response {
    String message;

    public Response(String message) {
        this.message = message;
    }

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
