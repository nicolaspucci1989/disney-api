package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PersonajeDTO {
  private Long id;

  @NotBlank(message = "Imagen es requerido")
  private String imagen;

  @NotBlank(message = "Nombre es requerido")
  private String nombre;

  @NotNull(message = "Edad es requerido")
  private Integer edad;

  @NotNull(message = "Peso es requerido")
  private Float peso;

  @NotBlank(message = "Historia es requerido")
  private String historia;

  private List<PeliculaDTO> peliculas;
}
