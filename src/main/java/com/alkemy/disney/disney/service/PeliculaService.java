package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface PeliculaService {
  MovieDTO save(MovieDTO dto);

  List<MovieBasicDTO> getAll(String name, Long idGenre, String order);

  MovieDTO getDetailsById(Long id);

  void delete(Long id);

  void addPersonaje(Long id, Long idPersonaje);

  void removePersonaje(Long id, Long idPersonaje);

  MovieDTO update(Long id, MovieDTO pelicula);
}
