package com.growdev.ecommerce.dto;

import com.growdev.ecommerce.entities.City;
import com.growdev.ecommerce.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
  private Long id;
  @NotBlank(message = "O campo é obrigatório")
  private String name;
  @Future
  @NotBlank(message = "O campo é obrigatório")
  private LocalDate date;
  private String url;
  @Column(nullable = false)
  private CityDTO cityDTO;

  public EventDTO(Event entity) {
    this.id = entity.getId();
    this.name = entity.getName();
    this.date = entity.getDate();
    this.url = entity.getUrl();
    this.cityDTO = new CityDTO(entity.getCity());
  }

  public EventDTO(Event entity, City city) {
    this(entity);
    this.cityDTO = new CityDTO(city);
  }
}
