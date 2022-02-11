package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {
  MovieDTO save(MovieDTO dto);

  List<MovieBasicDTO> getAll(String name, Long idGenre, String order);

  MovieDTO getDetailsById(Long id);

  void delete(Long id);

  void addCharacter(Long id, Long idCharacter);

  void removeCharacter(Long id, Long idCharacter);

  MovieDTO update(Long id, MovieDTO movie);
}
