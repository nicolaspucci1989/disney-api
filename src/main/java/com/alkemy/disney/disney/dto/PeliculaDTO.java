package com.alkemy.disney.disney.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {
  private Long id;

  @NotBlank(message = "Image is mandatory")
  private String imagen;

  @NotBlank(message = "Image is mandatory")
  private String titulo;

  @NotNull(message = "Fecha es mandatorio")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate fechaDeCreacion;

  @NotNull(message = "Rango es mandatorio")
  @Range(max = 5, message = "Rango no valido")
  private Integer calificacion;

  private List<PersonajeDTO> personajes;

  private Long generoId;
}
