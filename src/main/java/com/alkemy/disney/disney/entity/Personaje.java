package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Personaje {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String imagen;

  private String nombre;

  private Integer edad;

  private Float peso;

  private String historia;

  @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
  private Set<Movie> movies = new HashSet<>();

  private boolean deleted = Boolean.FALSE;

}
