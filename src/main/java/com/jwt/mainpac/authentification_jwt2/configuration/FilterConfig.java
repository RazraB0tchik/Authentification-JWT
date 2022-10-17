package com.jwt.mainpac.authentification_jwt2.configuration;

import com.jwt.mainpac.authentification_jwt2.filter.FilterJWT;
import com.jwt.mainpac.authentification_jwt2.filter.FilterProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> { //данный метод настраивает для разных url разную цепочку фильтров

    private FilterProvider filterProvider;

    public FilterConfig(FilterProvider filterProvider) {
        this.filterProvider = filterProvider;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        FilterJWT filterJWT = new FilterJWT(filterProvider); //создаем фильтр и запихиваем в очередь
        httpSecurity.addFilterBefore(filterJWT, UsernamePasswordAuthenticationFilter.class);
    }
}
