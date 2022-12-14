package com.growdev.ecommerce.entities;

import com.growdev.ecommerce.dto.CityDTO;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private Instant criado;
    private Instant atualizado;

    @OneToMany(mappedBy = "city")
    private Set<Event> events = new HashSet<>();

    @PrePersist
    public void prePersist() {
        criado = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        atualizado = Instant.now();
    }

    public City(CityDTO cityDTO){
        this.name = cityDTO.getName();
    }
}
