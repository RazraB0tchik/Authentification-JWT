package com.jwt.mainpac.authentification_jwt2.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String msg, Throwable cause) { //создает ошибку с сообщением и причиной
        super(msg, cause);
    }

    public AuthException(String msg) {
        super(msg);
    }

}
