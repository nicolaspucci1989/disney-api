package com.alkemy.disney.disney.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
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
      inverseJoinColumns = @JoinColumn(name = "personaje_id"))
  private Set<Personaje> personajes = new HashSet<>();

  // TODO: ver cascade
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "genero_id", insertable = false, updatable = false)
  private Genero genero;

  // TODO: ver nullable
  @Column(name = "genero_id", nullable = false)
  private Long generoId;

  private boolean deleted = Boolean.FALSE;

  public void addPersonaje(Personaje personaje) {
    personajes.add(personaje);
  }

  public void removePersonaje(Personaje personaje) {
    personajes.remove(personaje);
  }
}
