package com.jwt.mainpac.authentification_jwt2.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class FilterJWT implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest.getContentLength());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
