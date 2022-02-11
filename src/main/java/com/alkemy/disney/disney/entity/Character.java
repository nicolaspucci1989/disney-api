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
@Table(name = "character_entity")
@SQLDelete(sql = "UPDATE character SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Character {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String image;

  private String name;

  private Integer age;

  private Float peso;

  private String history;

  @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
  private Set<Movie> movies = new HashSet<>();

  private boolean deleted = Boolean.FALSE;

}
