package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDTO {
  private Long id;
  @NotBlank(message = "A role é obrigatória.")
  private String authority;

  public RoleDTO(Role entity) {
    this.id = entity.getId();
    this.authority = entity.getAuthority();
  }
}
