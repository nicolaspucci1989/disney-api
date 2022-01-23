package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;

import java.util.List;

public interface PeliculaService {
  PeliculaDTO save(PeliculaDTO dto);

  List<PeliculaBasicDTO> getAll();

  PeliculaDTO getDetailsById(Long id);

  void delete(Long id);

  void addPersonaje(Long id, Long idPersonaje);

  void removePersonaje(Long id, Long idPersonaje);
}
