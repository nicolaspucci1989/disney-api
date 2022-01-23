package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeliculaFilterDTO {
  private String name;
  private Long idGenre;
  private String order;

  public boolean isAsc() {
    return order.compareToIgnoreCase("ASC") == 0;
  }
}
