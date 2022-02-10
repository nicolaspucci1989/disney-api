package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genero;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

  private final GeneroMapper generoMapper;
  private final GeneroRepository generoRepository;

  @Autowired
  public GeneroServiceImpl(GeneroMapper generoMapper, GeneroRepository generoRepository) {
    this.generoMapper = generoMapper;
    this.generoRepository = generoRepository;
  }

  public GenreDTO save(GenreDTO dto) {
    Genero genero = generoMapper.generoDTO2Entity(dto);
    Genero entitySave = this.generoRepository.save(genero);
    return this.generoMapper.generoEntity2DTO(entitySave);
  }
}
