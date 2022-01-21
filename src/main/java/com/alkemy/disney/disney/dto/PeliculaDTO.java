package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {
  private Long id;
  private String imagen;
  private String titulo;
  private LocalDate fechaDeCreacion;
  private Integer calificacion;
  private List<PersonajeDTO> personajes;
  private Long generoId;
}
