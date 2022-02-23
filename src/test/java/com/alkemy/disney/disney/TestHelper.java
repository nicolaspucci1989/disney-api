package com.alkemy.disney.disney;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestHelper {
  public static MovieDTO getMovieDTO() {
    return MovieDTO.builder()
        .characters(new ArrayList<>())
        .title("Title")
        .genreId(1L)
        .creationDate(LocalDate.of(2000, 1, 1))
        .image("/img/movie.jpg")
        .rating(5)
        .build();
  }

  public static CharacterDTO getCharacterDTO() {
    return CharacterDTO.builder()
        .image("/img/character.jpg")
        .name("Character")
        .age(30)
        .weight(90f)
        .history("History")
        .movies(new ArrayList<>())
        .build();
  }

  public static ObjectMapper getMapper() {
    var mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }
}
