package com.jwt.mainpac.authentification_jwt2.filter;

import com.jwt.mainpac.authentification_jwt2.entity.RefreshToken;
import com.jwt.mainpac.authentification_jwt2.entity.User;
//import com.jwt.mainpac.authentification_jwt2.exceptions.JwtAuthenticationException;
import com.jwt.mainpac.authentification_jwt2.exceptions.JwtAuthenticationException;
import com.jwt.mainpac.authentification_jwt2.exceptions.TokenRequiredException;
import com.jwt.mainpac.authentification_jwt2.repositories.UserRepository;
import com.jwt.mainpac.authentification_jwt2.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class FilterProvider {

    @Value(value = "${jwt.token.secret}")
    private String secretKey;

    @Value(value = "${jwt.token.expired}")
    private long expire;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @PostConstruct //метод будет вызван только один раз при инициализации компонента
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

  public String createToken(String username, String role){
      Date now = new Date();
      Date validData = new Date(now.getTime()+expire);

      Claims claims = Jwts.claims().setSubject(username);
      System.out.println(claims.getSubject() + " SUBJECT");
      claims.put("role", role);

      return Jwts.builder()
              .setClaims(claims)
              .setExpiration(validData)
              .setIssuedAt(now)
              .signWith(SignatureAlgorithm.HS256, secretKey)
              .compact();
  }

  public Authentication authenticationToken(String token){ //этот метод сработает только если есть у пользователя токен
      String username = getSubject(token);
      UserDetails userDetails = userService.loadUserByUsername(username);
      return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()); //обновляет UserPass.. тем самым полностью заполняя информацию о юзере
  }

  public String getSubject(String token){ //достает из токена имя пользователя
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolve(HttpServletRequest httpServletRequest){
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if((bearerToken != null) && (bearerToken.startsWith("Bearer_"))){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
  }


  public boolean checkRef(String token) throws TokenRequiredException, AuthenticationException {
//        try {
            System.out.println("insideCheckRef");
            System.out.println(token + " check");

            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            User user = userRepository.findUserByUserName(claims.getBody().getSubject());
            RefreshToken refreshToken = user.getRefreshToken();

            System.out.println(refreshToken.getExpiredDate() + "asdasd");

            if (refreshToken.getExpiredDate().before(new Date())) {
                System.out.println("FK its trouble token!!");
                throw new TokenRequiredException("invalid token refresh");
            } else {
                System.out.println("Im normal men!");
                return true;
            }
//        }
//        catch (AuthenticationException exception){
//            throw exception;
//        }
}

  public boolean validate(String token) throws AuthenticationException {
            try {
                System.out.println("in validate");
                Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
                System.out.println(claims.getBody().getExpiration() + " in valid");
                //если дата перед текущей, то збс
                //                throw new TokenAccessException("Access token expired or invalid");
                if (claims.getBody().getExpiration().before(new Date())) {
                    System.out.println("IM false");
                    return false;
                } else {
                    System.out.println("IM true");
                    return true;
                }
            } catch (JwtException | IllegalArgumentException e) {
                 throw new JwtAuthenticationException("Invalid access token");
    }
  }
}
