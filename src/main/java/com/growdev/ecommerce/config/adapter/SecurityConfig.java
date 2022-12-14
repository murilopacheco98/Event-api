//package com.growdev.ecommerce.config.adapter;//package com.growdev.ecommerce.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration //classe é de configuração
//@EnableWebSecurity //websecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//  // As injeções foram feitas para que o spring security entenda que ao logar
//  // ele receberá o userDetailsService e uma senha criptografada.
//  @Autowired
//  private UserDetailsService userDetailsService;
//  @Autowired
//  private BCryptPasswordEncoder passwordEncoder;
//
//  //OAUTH
//  @Override
//  public void configure(WebSecurity web) throws Exception {
//    web.ignoring().antMatchers("/actuator/**"); //liberei todos endpoints
//  }
//
//  //ELE RECEBE A AUTENTICACAO
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    //useDetailService + senha criptografada
//    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//  }
//
//  //Seja bem vindo, ele retorna sua autenticacao
//  @Override
//  @Bean //vamos precisar da autenticação para que tenhamos a autorização
//  protected AuthenticationManager authenticationManager() throws Exception {
//    return super.authenticationManager();
//  }
//
//  @Bean
//  @Override
//  protected UserDetailsService userDetailsService() {
//    UserDetails admin = User.builder()
//            .username("admin")
//            .password(passwordEncoder.encode("admin"))
//            .roles("ADMIN")
//            .build();
//    return new InMemoryUserDetailsManager(admin);
//  }
//}
