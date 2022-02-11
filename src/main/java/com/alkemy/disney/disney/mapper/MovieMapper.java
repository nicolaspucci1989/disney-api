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
    entity.setImage(dto.getImage());
    entity.setTitle(dto.getTitle());
    entity.setRating(dto.getRating());
    entity.setCreationDate(dto.getCreationDate());
    entity.setGenreId(dto.getGeneroId());
    return entity;
  }

  public MovieDTO peliculaEntity2DTO(Movie entity) {
    MovieDTO dto = new MovieDTO();
    dto.setCreationDate(entity.getCreationDate());
    dto.setId(entity.getId());
    dto.setImage(entity.getImage());
    dto.setTitle(entity.getTitle());
    dto.setCreationDate(entity.getCreationDate());
    dto.setRating(entity.getRating());
    dto.setGeneroId(entity.getGenreId());
    return dto;
  }

  public List<MovieDTO> peliculaEntityList2DTOList(Set<Movie> entities) {
    return entities.stream().map(this::peliculaEntity2DTO).collect(Collectors.toList());
  }

  public List<MovieBasicDTO> pelicualEntityList2BasicDTOList(List<Movie> entities) {
    return entities.stream().map(e ->
            MovieBasicDTO.builder()
                .image(e.getImage())
                .title(e.getTitle())
                .creationDate(e.getCreationDate())
                .build()
        )
        .collect(Collectors.toList());
  }

  public void peliculaEntityRefreshValues(Movie entity, MovieDTO movieDTO) {
    entity.setImage(movieDTO.getImage());
    entity.setTitle(movieDTO.getTitle());
    entity.setCreationDate(movieDTO.getCreationDate());
    entity.setRating(movieDTO.getRating());
    entity.setGenreId(entity.getGenreId());
  }
}
