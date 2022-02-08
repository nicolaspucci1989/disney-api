package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PersonajeMapper {

  @Autowired
  private PeliculaMapper peliculaMapper;

  public Personaje personajeDTO2Entity(PersonajeDTO dto) {
    Personaje entity = new Personaje();
    entity.setImagen(dto.getImagen());
    entity.setNombre(dto.getNombre());
    entity.setEdad(dto.getEdad());
    entity.setPeso(dto.getPeso());
    entity.setHistoria(dto.getHistoria());
    return entity;
  }

  public PersonajeDTO personajeEntity2DTO(Personaje entity, boolean loadPeliculas) {
    PersonajeDTO dto = new PersonajeDTO();
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setNombre(entity.getNombre());
    dto.setEdad(entity.getEdad());
    dto.setPeso(entity.getPeso());
    dto.setHistoria(entity.getHistoria());
    if (loadPeliculas) {
      List<PeliculaDTO> peliculaDTOS = this.peliculaMapper.peliculaEntityList2DTOList(entity.getPeliculas(), false);
      dto.setPeliculas(peliculaDTOS);
    }
    return dto;
  }

  public Set<Personaje> personajeDTOList2Entity(List<PersonajeDTO> dtos) {
    Set<Personaje> entities = new HashSet<>();
    dtos.forEach(dto -> entities.add(this.personajeDTO2Entity(dto)));
    return entities;
  }

  public List<PersonajeDTO> personajeEntitySet2DTOList(Collection<Personaje> entities, boolean loadPeliculas) {
    return entities.stream()
        .map(e -> personajeEntity2DTO(e, loadPeliculas))
        .collect(Collectors.toList());
  }

  public List<PersonajeBasicDTO> personajeEntityList2BasicDTOList(Collection<Personaje> entities) {
    List<PersonajeBasicDTO> dtos = new ArrayList<>();
    PersonajeBasicDTO basicDTO;
    for (Personaje entity : entities) {
      basicDTO = new PersonajeBasicDTO();
      basicDTO.setImagen(entity.getImagen());
      basicDTO.setNombre(entity.getNombre());
      dtos.add(basicDTO);
    }
    return dtos;
  }

  public void personajeEntityRefreshValues(Personaje entity, PersonajeDTO personajeDTO) {
    entity.setHistoria(personajeDTO.getHistoria());
    entity.setNombre(personajeDTO.getNombre());
    entity.setImagen(personajeDTO.getImagen());
    entity.setPeso(personajeDTO.getPeso());
    entity.setEdad(personajeDTO.getEdad());
  }
}
