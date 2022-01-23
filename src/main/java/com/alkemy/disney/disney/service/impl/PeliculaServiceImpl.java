package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

  private final PeliculaRepository peliculaRepository;
  private final PeliculaMapper peliculaMapper;

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

  @Override
  public PeliculaDTO getDetailsById(Long id) {
    return this.peliculaRepository.findById(id)
        .map(e -> this.peliculaMapper.peliculaEntity2DTO(e, true))
        .orElseThrow(() -> new ParamNotFound("Id de pelicula no valido"));
  }

  @Override
  public void delete(Long id) {
    this.peliculaRepository.deleteById(id);
  }

  @Override
  public PeliculaEntity getEntityById(Long id) {
    return this.peliculaRepository.getById(id);
  }
}
