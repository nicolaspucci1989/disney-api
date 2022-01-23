package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class PeliculaFilterDTO {
  private String name;
  private Long idGenre;
  private String order;

  public boolean isAsc() {
//    return order.toLowerCase(Locale.ROOT).equals("asc");
    return order.compareToIgnoreCase("ASC") == 0;
  }

  public boolean isDesc() {
    return order.toLowerCase(Locale.ROOT).equals("desc");
  }
}
