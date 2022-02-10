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
public class MovieDTO {
  private Long id;

  @NotBlank(message = "Imagen es requerido")
  private String imagen;

  @NotBlank(message = "Titulo es requerido")
  private String titulo;

  @NotNull(message = "Fecha es requerido")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate fechaDeCreacion;

  @NotNull(message = "Rango es requerido")
  @Range(max = 5, message = "Rango no valido")
  private Integer calificacion;

  private List<PersonajeDTO> personajes;

  @NotNull(message = "Genero es requerido")
  private Long generoId;
}
