package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
  public Genre generoDTO2Entity(GenreDTO dto) {
    Genre entity = new Genre();
    entity.setImagen(dto.getImagen());
    entity.setNombre(dto.getNombre());
    return entity;
  }

  public GenreDTO generoEntity2DTO(Genre entity) {
    GenreDTO dto = new GenreDTO();
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setNombre(entity.getNombre());
    return dto;
  }
}
