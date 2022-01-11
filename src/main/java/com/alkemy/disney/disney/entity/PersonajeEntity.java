package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter
public class PersonajeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String imagen;

  private String nombre;

  private Integer edad;

  private Float peso;

  private String historia;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
  @JoinTable(
      name = "personaje_peliculas",
      joinColumns = @JoinColumn(name = "personaje_id"),
      inverseJoinColumns = @JoinColumn(name = "pelicula_id")
  )
  private Set<PeliculaEntity> peliculas = new HashSet<>();
}
