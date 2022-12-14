package com.growdev.ecommerce.entities;

import com.growdev.ecommerce.dto.EventDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "EVENT")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate date;
    private String url;
    private Instant criado;
    private Instant atualizado;

    @PrePersist
    public void prePersist() {
        criado = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        atualizado = Instant.now();
    }
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Event(EventDTO eventDTO) {
        this.name = eventDTO.getName();
        this.date = eventDTO.getDate();
        this.url = eventDTO.getUrl();
        this.city = new City(eventDTO.getCityDTO());
    }
}
