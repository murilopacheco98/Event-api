package com.growdev.ecommerce.dto.user;

import com.growdev.ecommerce.dto.user.UserDTO;
import com.growdev.ecommerce.validation.user.UserInsertValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@UserInsertValidation
@Getter
@Setter
public class UserInsertDTO extends UserDTO {
  @NotBlank(message = "A senha é obrigatória.")
  private String senha;
}
