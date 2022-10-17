package com.jwt.mainpac.authentification_jwt2.filter;

import com.jwt.mainpac.authentification_jwt2.exceptions.AuthException;
import com.jwt.mainpac.authentification_jwt2.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
public class FilterProvider {

    @Value(value = "${jwt.token.secret}")
    private String secretKey;

    @Value(value = "${jwt.token.expired}")
    private long expire;

    @Autowired
    UserService userService;

    @PostConstruct //метод будет вызван только один раз при инициализации компонента
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

  public String createToken(String username, String role){
      System.out.println("im inside creator" + username+" "+role);
      Claims claims = Jwts.claims().setSubject(username);
      System.out.println(claims);
      claims.put("role", role);

      Date now = new Date();
      Date validData = new Date(now.getTime()+expire);

      return Jwts.builder()
              .setClaims(claims)
              .setExpiration(validData)
              .setIssuedAt(now)
              .signWith(SignatureAlgorithm.HS256, secretKey)
              .compact();
  }

  public Authentication authenticationToken(String token){ //этот метод сработает только если есть у пользователя токен
      UserDetails userDetails = userService.loadUserByUsername(getSubject(token));
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

  public boolean validate(String token){
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      try{
            if (claims.getBody().getExpiration().before(new Date())){ //если дата перед текущей, то збс
                return false;
            }
            else {
                return true;
            }
        }
        catch (JwtException | IllegalArgumentException e){
            System.out.println("Bad");
            System.out.println("secur"+SecurityContextHolder.getContext().getAuthentication().getName());
            throw new AuthException("Jwt token is expired or invalid");
        }
  }
}
