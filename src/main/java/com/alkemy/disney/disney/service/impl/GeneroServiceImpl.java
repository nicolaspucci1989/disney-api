package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
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
    Genre genre = generoMapper.generoDTO2Entity(dto);
    Genre entitySave = this.generoRepository.save(genre);
    return this.generoMapper.generoEntity2DTO(entitySave);
  }
}
