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

  private String title;

  @Column(name = "creation_date")
  private LocalDate creationDate;

  private Integer rating;

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

  public void addCharacter(Character character) {
    characters.add(character);
  }

  public void removeCharacter(Character character) {
    characters.remove(character);
  }
}
