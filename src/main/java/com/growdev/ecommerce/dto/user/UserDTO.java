package com.growdev.ecommerce.dto.user;

import com.growdev.ecommerce.dto.RoleDTO;
import com.growdev.ecommerce.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
  private Long id;
  @NotBlank(message = "Campo Obrigatório")
  private String nome;
  @NotBlank(message = "O campo é obrigatório")
  private String email;
  private Set<RoleDTO> roles = new HashSet<>();
  public UserDTO(UserEntity entity) {
    this.id = entity.getId();
    this.nome = entity.getNome();
    this.email = entity.getEmail();
    entity.getRoles().forEach(r -> this.roles.add(new RoleDTO(r)));
  }
}
