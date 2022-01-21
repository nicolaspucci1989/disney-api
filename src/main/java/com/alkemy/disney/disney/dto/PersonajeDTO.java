package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeDTO {
  private Long id;
  private String imagen;
  private String nombre;
  private Integer edad;
  private Float peso;
  private String historia;
  private List<PeliculaDTO> peliculas;
}
