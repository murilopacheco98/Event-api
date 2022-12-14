package com.growdev.ecommerce.dto.user;

import com.growdev.ecommerce.dto.user.UserDTO;
import com.growdev.ecommerce.validation.user.UserUpdateValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@UserUpdateValidation
@Getter
@Setter
public class UserUpdateDTO extends UserDTO {
    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
}
