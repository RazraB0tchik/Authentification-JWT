package com.jwt.mainpac.authentification_jwt2.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterJWT extends GenericFilterBean { //это базовый класс реализации интерфейса javax.servlet.Filter, ифльтр созданный через этот абстрактый класс получает поддержку spring

    private FilterProvider filterProvider;

    public FilterJWT(FilterProvider filterProvider) {
        this.filterProvider = filterProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FILTER!!!!!!!");
        String token = filterProvider.resolve((HttpServletRequest) servletRequest); //вытащил токен из заголовка
        System.out.println("take token");
        System.out.println();
        if ((token != null) && (filterProvider.validate(token))){
            Authentication authentication = filterProvider.authenticationToken(token);
            System.out.println("ok, filter");

            if (authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication); //если все хорошо то мы   System.out.println("adasdasda");заносим в контекст данне о пользователе
                System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
