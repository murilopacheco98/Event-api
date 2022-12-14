package com.growdev.ecommerce.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class UserEntity implements UserDetails {//vai ser a classe que retorna o acesso - spring security
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  @Column(unique = true)
  private String email;
  private String senha;
  @ManyToMany(fetch = FetchType.EAGER) // so pra forçar a buscar do usuario e nele vir as roles
  @JoinTable(
    name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();

  //TODOS OS MÉTODOS PARA TRABALHAR COM SEGURANÇA NO SPRING
  //Esse método é reponsável pela coleção de perfis para o usuário
  //esse método que diz ao spring security quem são os níveis de acesso
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //PERCORRENDO A LIST DE ROLES E MANDANDO ELA CONVERTIDA EM UMA LISTA DE GRANTEDAUTHORITY
    //(ADMIN, CLIENT)
    return roles.stream().map(role->new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public String getUsername() {
    return email;
  }

  //COLOQUEI TODOS COMO TRUE, POIS O JWT + OAUTH já farão tudo isso, entao nao preciso de um algoritmo logico
  @Override
  public boolean isAccountNonExpired() {//TESTA SE A CONTA EXPIRADA
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {//Testa se o usuário está bloqueado
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {//Testa se as credenciais estão expiradas
    return true;
  }

  @Override
  public boolean isEnabled() {//habilita ou nao o usuario no sistema
    return true;
  }
}
