package com.jwt.mainpac.authentification_jwt2.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jwt.mainpac.authentification_jwt2.exceptions.JwtAuthenticationException;
import com.jwt.mainpac.authentification_jwt2.exceptions.JwtAuthenticationException;
import com.jwt.mainpac.authentification_jwt2.exceptions.TokenRequiredException;
import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class FilterJWT extends OncePerRequestFilter { //это базовый класс реализации интерфейса javax.servlet.Filter, ифльтр созданный через этот абстрактый класс получает поддержку spring

    @Autowired
    UserRepository userRepository;

    private FilterProvider filterProvider;

    @Autowired
    ObjectMapper objectMapper;

    @Value(value = "${jwt.token.secret}")
    private String secretKey;

    public FilterJWT(FilterProvider filterProviderNew) {
        this.filterProvider = filterProviderNew;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, TokenRequiredException, JwtAuthenticationException {
        try {

            System.out.println("FILTER!!!!!!!");
            String token = filterProvider.resolve(request); //вытащил токен из заголовка
            System.out.println(token + "token");//проверим актуальность ref
            if ((token != null)  && (filterProvider.validate(token))&& (filterProvider.checkRef(token))) {
                System.out.println("Im get Errore TOken!");
                Authentication authentication = filterProvider.authenticationToken(token);

                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication); //если все хорошо то мы   System.out.println("adasdasda");заносим в контекст данне о пользователе
                }
            }

            filterChain.doFilter(request, response);
        }
        catch (JwtAuthenticationException e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(converObjectToJson(e.getMessage()));
        }
        catch (RuntimeException e){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(converObjectToJson(e.getMessage()));
        }
    }

    public String converObjectToJson(Object object) throws JsonProcessingException {
        if (object==null){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper(); //класс, выполняющий сериализацию
        return mapper.writeValueAsString(object);
    }


}
