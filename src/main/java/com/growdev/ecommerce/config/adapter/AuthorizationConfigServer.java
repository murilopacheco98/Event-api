//package com.growdev.ecommerce.config.adapter;//package com.growdev.ecommerce.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
////CLASSE DE AUTENTICACAO, QUE GERA O TOKEN (ID, SECRET,
//// DURATION)
////ESTOU CONFIGURANDO O MEU PROJETO SPRING PARA DIZER A ELE QUE ELE USARÁ PARA SE AUTENTICAR NO SISTEMA
////TODOS OS BEANS INJETADOS E OS MÉTODOS PADRÕES DO OAUT
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationConfigServer extends AuthorizationServerConfigurerAdapter {
//
//  //Injetar objetos de configuração para meu OAuth
//  @Autowired
//  private JwtAccessTokenConverter accessTokenConverter;//Tenho o objeto com segredo de token
//  @Autowired
//  private JwtTokenStore tokenStore;//retorna o token/ler
//  @Autowired
//  private AuthenticationManager authenticationManager;//a autenticação que veio do spring security
//  @Autowired
//  private BCryptPasswordEncoder passwordEncoder;//criptografia
//  @Override
//  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//    //a segurança permitirá acesso ao sistema via checagem de token
//   security.tokenKeyAccess("permitAll()").checkTokenAccess("isAutheticated()");
//  }
//
//  // definindo as credencias da minha aplicação que usa OAuth
//  @Override
//  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//    clients.inMemory() //a minha aplicação roda as credenciais em memoria
//      .withClient("growdev") // definir o clientId, ou seja, é o nome de autenticacao para as credencias do sistema
//      .secret(passwordEncoder.encode("senhadotoken")) // definir a senha da aplicação
//      .scopes("read", "write") // acesso sempre vai ser com base em leitura e escrita
//      .authorizedGrantTypes("password") // defini os tipos de autenticacao na hora de logar
//      .accessTokenValiditySeconds(84000);// 1 minuto e uns quebradinhos
//  }
//
//  //Autorizar os edpoints pelo token
//  @Override
//  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    endpoints.authenticationManager(authenticationManager)//é a autenticação para os endpoins
//      .tokenStore(tokenStore)
//      .accessTokenConverter(accessTokenConverter);//quais objetos reponsaveis por processar
//
//  }
//}
