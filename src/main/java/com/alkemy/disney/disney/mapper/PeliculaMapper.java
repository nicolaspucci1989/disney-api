package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import com.alkemy.disney.disney.entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PeliculaMapper {

  @Autowired
  private PersonajeMapper personajeMapper;

  public Pelicula peliculaDTO2Entity(PeliculaDTO dto) {
    Pelicula entity = new Pelicula();
    entity.setImagen(dto.getImagen());
    entity.setTitulo(dto.getTitulo());
    entity.setCalificacion(dto.getCalificacion());
    entity.setFechaDeCreacion(dto.getFechaDeCreacion());
    entity.setGeneroId(dto.getGeneroId());
    // personajes
    Set<Personaje> personajes = this.personajeMapper.personajeDTOList2Entity(dto.getPersonajes());
    entity.setPersonajes(personajes);
    return entity;
  }

  public PeliculaDTO peliculaEntity2DTO(Pelicula entity, boolean loadPersonajes) {
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

  public List<PeliculaDTO> peliculaEntityList2DTOList(Set<Pelicula> entities, boolean loadPersonajes) {
    return entities.stream().map(pelicula -> peliculaEntity2DTO(pelicula, loadPersonajes)).collect(Collectors.toList());
  }

  public List<PeliculaBasicDTO> pelicualEntityList2BasicDTOList(List<Pelicula> entities) {
    return entities.stream().map(e ->
            PeliculaBasicDTO.builder()
                .imagen(e.getImagen())
                .titulo(e.getTitulo())
                .fechaDeCreacion(e.getFechaDeCreacion())
                .build()
        )
        .collect(Collectors.toList());
  }

  public void peliculaEntityRefreshValues(Pelicula entity, PeliculaDTO peliculaDTO) {
    entity.setImagen(peliculaDTO.getImagen());
    entity.setTitulo(peliculaDTO.getTitulo());
    entity.setFechaDeCreacion(peliculaDTO.getFechaDeCreacion());
    entity.setCalificacion(peliculaDTO.getCalificacion());
    entity.setGeneroId(entity.getGeneroId());
  }
}
