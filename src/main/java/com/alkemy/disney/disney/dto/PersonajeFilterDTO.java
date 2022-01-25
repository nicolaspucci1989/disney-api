package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PersonajeFilterDTO {
  private String name;
  private Integer edad;
  private List<Long> idMovies;
}
