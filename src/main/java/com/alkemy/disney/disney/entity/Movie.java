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
@Getter
@Setter
@SQLDelete(sql = "UPDATE movie SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Movie {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String image;

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
      name = "movie_character",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "character_id"))
  private Set<Character> characters = new HashSet<>();

  // TODO: ver cascade
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "genre_id", insertable = false, updatable = false)
  private Genre genre;

  // TODO: ver nullable
  @Column(name = "genre_id", nullable = false)
  private Long genreId;

  private boolean deleted = Boolean.FALSE;

  public void addPersonaje(Character character) {
    characters.add(character);
  }

  public void removePersonaje(Character character) {
    characters.remove(character);
  }
}
