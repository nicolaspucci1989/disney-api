package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

  public Movie peliculaDTO2Entity(MovieDTO dto) {
    Movie entity = new Movie();
    entity.setImagen(dto.getImagen());
    entity.setTitulo(dto.getTitulo());
    entity.setCalificacion(dto.getCalificacion());
    entity.setFechaDeCreacion(dto.getFechaDeCreacion());
    entity.setGenreId(dto.getGeneroId());
    return entity;
  }

  public MovieDTO peliculaEntity2DTO(Movie entity) {
    MovieDTO dto = new MovieDTO();
    dto.setFechaDeCreacion(entity.getFechaDeCreacion());
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setTitulo(entity.getTitulo());
    dto.setFechaDeCreacion(entity.getFechaDeCreacion());
    dto.setCalificacion(entity.getCalificacion());
    dto.setGeneroId(entity.getGenreId());
    return dto;
  }

  public List<MovieDTO> peliculaEntityList2DTOList(Set<Movie> entities) {
    return entities.stream().map(this::peliculaEntity2DTO).collect(Collectors.toList());
  }

  public List<MovieBasicDTO> pelicualEntityList2BasicDTOList(List<Movie> entities) {
    return entities.stream().map(e ->
            MovieBasicDTO.builder()
                .imagen(e.getImagen())
                .titulo(e.getTitulo())
                .fechaDeCreacion(e.getFechaDeCreacion())
                .build()
        )
        .collect(Collectors.toList());
  }

  public void peliculaEntityRefreshValues(Movie entity, MovieDTO movieDTO) {
    entity.setImagen(movieDTO.getImagen());
    entity.setTitulo(movieDTO.getTitulo());
    entity.setFechaDeCreacion(movieDTO.getFechaDeCreacion());
    entity.setCalificacion(movieDTO.getCalificacion());
    entity.setGenreId(entity.getGenreId());
  }
}
