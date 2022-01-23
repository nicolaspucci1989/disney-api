package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Pelicula {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String imagen;

  private String titulo;

  @Column(name = "fecha_de_creacion")
  private LocalDate fechaDeCreacion;

  private Integer calificacion;

  @ManyToMany(
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
  @JoinTable(
      name = "pelicula_personaje",
      joinColumns = @JoinColumn(name = "pelicula_id"),
      inverseJoinColumns = @JoinColumn(name = "personaje_id")
  )
  private Set<Personaje> personajes = new HashSet<>();

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "genero_id", insertable = false, updatable = false)
  private Genero genero;

  @Column(name = "genero_id", nullable = false)
  private Long generoId;
}
