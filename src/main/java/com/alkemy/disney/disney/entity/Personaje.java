package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Personaje {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String imagen;

  private String nombre;

  private Integer edad;

  private Float peso;

  private String historia;

  @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
  private Set<Pelicula> peliculas = new HashSet<>();

  public void agregarPelicula(Pelicula pelicula) {
    this.peliculas.add(pelicula);
  }

  public void eliminarPelicula(Pelicula pelicula) {
    this.peliculas.remove(pelicula);
  }
}
