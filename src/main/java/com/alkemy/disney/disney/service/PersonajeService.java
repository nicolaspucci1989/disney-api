package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.Personaje;

import java.util.List;

public interface PersonajeService {
  PersonajeDTO save(PersonajeDTO dto);

  List<PersonajeBasicDTO> getAll(String name, Integer age, List<Long> idMovies);

  PersonajeDTO getDetailsById(Long id);

  PersonajeDTO update(Long id, PersonajeDTO personaje);

  void delete(Long id);

  Personaje getById(Long idPersonaje);
}
