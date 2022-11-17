package com.jwt.mainpac.authentification_jwt2.configuration;

import com.jwt.mainpac.authentification_jwt2.entity.User;
import com.jwt.mainpac.authentification_jwt2.filter.FilterJWT;
import com.jwt.mainpac.authentification_jwt2.filter.FilterProvider;
import com.jwt.mainpac.authentification_jwt2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Access;

@Configuration
@EnableWebSecurity//через эту анатацию включается защита SpringSecurity
public class SecurityConfig { //метод для настройки корс есть в интерфейсе WebMvcConfigurer

//    private final UserService userService;

//
//    private final FilterProvider filterProvider;

//    @Autowired
//    FilterJWT filterJWT;

    @Autowired
    UserService userService;

    private final FilterProvider filterProvider;

    public SecurityConfig(FilterProvider provider) {
//        this.filterProvider = filterProvider;
        this.filterProvider = provider;

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().and().csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST).permitAll()
////                .antMatchers("/auth/api/**", "/reg/registrationUser/").permitAll()
//                .anyRequest().authenticated();
////                .and()
////                .apply(new FilterConfig(filterProvider)); //добавили необходимый api в конфигурацию
//
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{ //сдесь по идее происходит конфигурация нашего SecurityFilterChain
//        httpSecurity.addFilterBefore(new FilterJWT(filterProvider), UsernamePasswordAuthenticationFilter.class);

        http
                .httpBasic().and().csrf().disable() //http Security конфигурация проверят ауентификацию пользователя для каждого запроса (если запрос был отправлен аутентифицированным пользователем то збс)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/api/login", "/reg/registrationUser", "/update/getAccess/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()//минуем проверку авторизации для option запросов
                .and()
                .authenticationProvider(daoAuthenticationProvider());
        http.addFilterBefore(new FilterJWT(filterProvider), UsernamePasswordAuthenticationFilter.class); //добавили фильтр в конфигурацию

        return http.build();
    }
}
