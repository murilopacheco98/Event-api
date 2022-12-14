//package com.growdev.ecommerce.config.adapter;//package com.growdev.ecommerce.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import java.util.Arrays;
//
////CLASSE QUE VAI PERMITIR POR NIVEL DE ACESSO
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//  @Autowired
//  private Environment env;
//
//  @Autowired
//  private JwtTokenStore tokenStore; //Eu inseri o token para uso de autorização do endpoint
//
//  //ONDE EU PERMITO MEUS ENDPOINTS
//  @Override
//  public void configure(HttpSecurity http) throws Exception {
//    http
//        .csrf().disable()
//        .authorizeRequests()
//        .antMatchers("/event/get/**").permitAll()
//        .antMatchers("/city/get/**").permitAll()
//        .antMatchers("/user/post/**").permitAll()
//        .antMatchers("/event/post", "/event/put").hasRole("user")
//        .antMatchers("/**").hasRole("ADMIN")
//        .anyRequest().authenticated() // os demais acessos deverao ser feitos login e senha
//        .and()
//        .httpBasic();
//  }
//}
