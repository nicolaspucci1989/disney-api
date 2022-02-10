package com.alkemy.disney.disney.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CharacterBasicDTO {
  private String imagen;
  private String nombre;
}
