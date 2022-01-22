package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
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

  public GeneroDTO save(GeneroDTO dto) {
    GeneroEntity generoEntity = generoMapper.generoDTO2Entity(dto);
    GeneroEntity entitySave = this.generoRepository.save(generoEntity);
    return this.generoMapper.generoEntity2DTO(entitySave);
  }
}
