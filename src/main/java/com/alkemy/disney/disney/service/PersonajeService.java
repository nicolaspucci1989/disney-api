package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Personaje;

import java.util.List;

public interface PersonajeService {
  CharacterDTO save(CharacterDTO dto);

  List<CharacterBasicDTO> getAll(String name, Integer age, List<Long> idMovies);

  CharacterDTO getDetailsById(Long id);

  CharacterDTO update(Long id, CharacterDTO personaje);

  void delete(Long id);

  Personaje getById(Long idPersonaje);
}
