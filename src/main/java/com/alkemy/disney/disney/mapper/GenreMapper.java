package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
  public Genre genreDTO2Entity(GenreDTO dto) {
    Genre entity = new Genre();
    entity.setImage(dto.getImage());
    entity.setName(dto.getName());
    return entity;
  }

  public GenreDTO genreEntity2DTO(Genre entity) {
    GenreDTO dto = new GenreDTO();
    dto.setId(entity.getId());
    dto.setImage(entity.getImage());
    dto.setName(entity.getName());
    return dto;
  }
}
