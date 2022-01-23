package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;

import java.util.List;

public interface PersonajeService {
  PersonajeDTO save(PersonajeDTO dto);

  List<PersonajeBasicDTO> getAll();

  PersonajeDTO getDetailsById(Long id);

  PersonajeDTO update(Long id, PersonajeDTO personaje);

  void delete(Long id);

  void addPelicula(Long id, Long idPelicula);
}
