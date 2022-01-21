package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PeliculaMapper {

  @Autowired
  private PersonajeMapper personajeMapper;

  public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto) {
    PeliculaEntity entity = new PeliculaEntity();
    entity.setImagen(dto.getImagen());
    entity.setTitulo(dto.getTitulo());
    entity.setCalificacion(dto.getCalificacion());
    entity.setFechaDeCreacion(dto.getFechaDeCreacion());
    entity.setGeneroId(dto.getGeneroId());
    // personajes
    Set<PersonajeEntity> personajes = this.personajeMapper.personajeDTOList2Entity(dto.getPersonajes());
    entity.setPersonajes(personajes);
    return entity;
  }

  public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean loadPersonajes) {
    PeliculaDTO dto = new PeliculaDTO();
    dto.setFechaDeCreacion(entity.getFechaDeCreacion());
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setTitulo(entity.getTitulo());
    dto.setFechaDeCreacion(entity.getFechaDeCreacion());
    dto.setCalificacion(entity.getCalificacion());
    dto.setGeneroId(entity.getGeneroId());
    if (loadPersonajes) {
      List<PersonajeDTO> personajeDTOS = this.personajeMapper.personajeEntitySet2DTOList(entity.getPersonajes(), false);
      dto.setPersonajes(personajeDTOS);
    }
    return dto;
  }

  public List<PeliculaDTO> peliculaEntityList2DTOList(Set<PeliculaEntity> entities, boolean loadPersonajes) {
    List<PeliculaDTO> dtos = new ArrayList<>();
    entities.forEach(entity -> dtos.add(this.peliculaEntity2DTO(entity, loadPersonajes)));
    return dtos;
  }

  public List<PeliculaBasicDTO> pelicualEntityList2BasicDTOList(List<PeliculaEntity> entities) {
    List<PeliculaBasicDTO> dtos = new ArrayList<>();
    PeliculaBasicDTO basicDTO;
    for (PeliculaEntity entity : entities) {
      basicDTO = new PeliculaBasicDTO();
      basicDTO.setImagen(entity.getImagen());
      basicDTO.setTitulo(entity.getTitulo());
      basicDTO.setFechaDeCreacion(entity.getFechaDeCreacion());
      dtos.add(basicDTO);
    }
    return dtos;
  }
}
