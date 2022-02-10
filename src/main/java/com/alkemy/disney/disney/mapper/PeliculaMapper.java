package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PeliculaMapper {

  public Pelicula peliculaDTO2Entity(MovieDTO dto) {
    Pelicula entity = new Pelicula();
    entity.setImagen(dto.getImagen());
    entity.setTitulo(dto.getTitulo());
    entity.setCalificacion(dto.getCalificacion());
    entity.setFechaDeCreacion(dto.getFechaDeCreacion());
    entity.setGeneroId(dto.getGeneroId());
    return entity;
  }

  public MovieDTO peliculaEntity2DTO(Pelicula entity) {
    MovieDTO dto = new MovieDTO();
    dto.setFechaDeCreacion(entity.getFechaDeCreacion());
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setTitulo(entity.getTitulo());
    dto.setFechaDeCreacion(entity.getFechaDeCreacion());
    dto.setCalificacion(entity.getCalificacion());
    dto.setGeneroId(entity.getGeneroId());
    return dto;
  }

  public List<MovieDTO> peliculaEntityList2DTOList(Set<Pelicula> entities) {
    return entities.stream().map(this::peliculaEntity2DTO).collect(Collectors.toList());
  }

  public List<MovieBasicDTO> pelicualEntityList2BasicDTOList(List<Pelicula> entities) {
    return entities.stream().map(e ->
            MovieBasicDTO.builder()
                .imagen(e.getImagen())
                .titulo(e.getTitulo())
                .fechaDeCreacion(e.getFechaDeCreacion())
                .build()
        )
        .collect(Collectors.toList());
  }

  public void peliculaEntityRefreshValues(Pelicula entity, MovieDTO movieDTO) {
    entity.setImagen(movieDTO.getImagen());
    entity.setTitulo(movieDTO.getTitulo());
    entity.setFechaDeCreacion(movieDTO.getFechaDeCreacion());
    entity.setCalificacion(movieDTO.getCalificacion());
    entity.setGeneroId(entity.getGeneroId());
  }
}
