package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.Genero;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {
  public Genero generoDTO2Entity(GeneroDTO dto) {
    Genero entity = new Genero();
    entity.setImagen(dto.getImagen());
    entity.setNombre(dto.getNombre());
    return entity;
  }

  public GeneroDTO generoEntity2DTO(Genero entity) {
    GeneroDTO dto = new GeneroDTO();
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setNombre(entity.getNombre());
    return dto;
  }
}
