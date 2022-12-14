package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.entities.City;
import com.growdev.ecommerce.validation.CityValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@CityValidation
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityDTO {
  private Long id;
  @NotBlank(message = "O campo é obrigatório")
  @Size(min = 3, max = 50, message = "Deve ter entre 3 a 50 caracteres")
  private String name;

  public CityDTO(City entity) {
    this.id = entity.getId();
    this.name = entity.getName();
  }

}
