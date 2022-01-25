package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class GeneroDTO {
  private Long id;

  @NotBlank(message = "Nombre es requerido")
  private String nombre;

  @NotBlank(message = "Imagen es requerido")
  private String imagen;
}
