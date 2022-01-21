package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.service.PeliculaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

  private PeliculaRepository peliculaRepository;
  private PeliculaMapper peliculaMapper;

  @Autowired
  public PeliculaServiceImpl(PeliculaMapper peliculaMapper, PeliculaRepository peliculaRepository) {
    this.peliculaMapper = peliculaMapper;
    this.peliculaRepository = peliculaRepository;
  }

  public PeliculaDTO save(PeliculaDTO dto) {
    PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(dto);
    PeliculaEntity entitySave = this.peliculaRepository.save(peliculaEntity);
    return this.peliculaMapper.peliculaEntity2DTO(entitySave, true);
  }

  @Override
  public List<PeliculaBasicDTO> getAll() {
    List<PeliculaEntity> all = peliculaRepository.findAll();
    return peliculaMapper.pelicualEntityList2BasicDTOList(all);
  }
}
