package com.jwt.mainpac.authentification_jwt2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class TokenRequiredException extends RuntimeException{
    public TokenRequiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TokenRequiredException(String msg) {
        super(msg);
    }
}
