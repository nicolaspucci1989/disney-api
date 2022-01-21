package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;

import java.util.List;

public interface PeliculaService {
  PeliculaDTO save(PeliculaDTO dto);

  List<PeliculaBasicDTO> getAll();
}
