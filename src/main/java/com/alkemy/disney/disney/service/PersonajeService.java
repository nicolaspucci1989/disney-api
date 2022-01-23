package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;

import java.util.List;

public interface PersonajeService {
  PersonajeDTO save(PersonajeDTO dto);

  List<PersonajeBasicDTO> getAll(String name);

  PersonajeDTO getDetailsById(Long id);

  PersonajeDTO update(Long id, PersonajeDTO personaje);

  void delete(Long id);

  PersonajeEntity getById(Long idPersonaje);
}
