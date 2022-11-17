//package com.jwt.mainpac.authentification_jwt2.configuration;
//
//import com.jwt.mainpac.authentification_jwt2.filter.FilterJWT;
//import com.jwt.mainpac.authentification_jwt2.filter.FilterProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//
//@Configuration
//public class FilterConfig {
////        extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> { //данный метод настраивает для разных url разную цепочку фильтров
//
//    private FilterProvider filterProvider;
////
//    public FilterConfig(FilterProvider filterProviderEnt) {
//        this.filterProvider = filterProviderEnt;
//    }
////
////    @Override
////    public void configure(HttpSecurity httpSecurity) throws Exception{
////        FilterJWT filterJWT = new FilterJWT(filterProvider); //создаем фильтр и запихиваем в очередь
////        httpSecurity.addFilterBefore(filterJWT, UsernamePasswordAuthenticationFilter.class);
////    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
//        httpSecurity.addFilterBefore(new FilterJWT(filterProvider), UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
//    }
//
//
//}
