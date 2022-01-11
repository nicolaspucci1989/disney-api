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

  @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
  private Set<PeliculaEntity> peliculas = new HashSet<>();

  public void agregarPelicula(PeliculaEntity pelicula) {
    this.peliculas.add(pelicula);
  }
  public void eliminarPelicula(PeliculaEntity pelicula) {
    this.peliculas.remove(pelicula);
  }
}
