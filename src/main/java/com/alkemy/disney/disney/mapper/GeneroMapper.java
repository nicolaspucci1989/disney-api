package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {
  public Genero generoDTO2Entity(GenreDTO dto) {
    Genero entity = new Genero();
    entity.setImagen(dto.getImagen());
    entity.setNombre(dto.getNombre());
    return entity;
  }

  public GenreDTO generoEntity2DTO(Genero entity) {
    GenreDTO dto = new GenreDTO();
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setNombre(entity.getNombre());
    return dto;
  }
}
