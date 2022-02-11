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

  @NotBlank(message = "Image is required")
  private String image;

  @NotBlank(message = "Title is required")
  private String title;

  @NotNull(message = "Fecha es requerido")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate fechaDeCreacion;

  @NotNull(message = "Rango es requerido")
  @Range(max = 5, message = "Rango no valido")
  private Integer calificacion;

  private List<CharacterDTO> personajes;

  @NotNull(message = "Genero es requerido")
  private Long generoId;
}
