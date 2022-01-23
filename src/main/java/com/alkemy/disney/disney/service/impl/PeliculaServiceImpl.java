package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.specifications.PeliculaSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

  private final PeliculaRepository peliculaRepository;
  private final PersonajeService personajeService;
  private final PeliculaMapper peliculaMapper;

  @Autowired
  public PeliculaServiceImpl(PeliculaMapper peliculaMapper, PeliculaRepository peliculaRepository, PersonajeService personajeService) {
    this.peliculaMapper = peliculaMapper;
    this.peliculaRepository = peliculaRepository;
    this.personajeService = personajeService;
  }

  public PeliculaDTO save(PeliculaDTO dto) {
    PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(dto);
    PeliculaEntity entitySave = this.peliculaRepository.save(peliculaEntity);
    return this.peliculaMapper.peliculaEntity2DTO(entitySave, true);
  }

  @Override
  public List<PeliculaBasicDTO> getAll(String name, Long idGenre, String order) {
    PeliculaFilterDTO peliculaFilterDTO = new PeliculaFilterDTO(name, idGenre, order);
    Specification<PeliculaEntity> spec = PeliculaSpecification.getByFilters(peliculaFilterDTO);
    List<PeliculaEntity> all = peliculaRepository.findAll(spec);
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
  public void addPersonaje(Long id, Long idPersonaje) {
    PeliculaEntity entity = peliculaRepository.getById(id);
    PersonajeEntity personajeEntity = personajeService.getById(idPersonaje);
    entity.addPersonaje(personajeEntity);
    peliculaRepository.save(entity);
  }

  @Override
  public void removePersonaje(Long id, Long idPersonaje) {
    PeliculaEntity entity = peliculaRepository.getById(id);
    PersonajeEntity personajeEntity = personajeService.getById(idPersonaje);
    entity.removePersonaje(personajeEntity);
    peliculaRepository.save(entity);
  }

  @Override
  public PeliculaDTO update(Long id, PeliculaDTO peliculaDTO) {
    Optional<PeliculaEntity> entity = peliculaRepository.findById(id);
    if (entity.isEmpty()) {
      throw new ParamNotFound("Id de presonaje no valido");
    }
    this.peliculaMapper.peliculaEntityRefreshValues(entity.get(), peliculaDTO);
    PeliculaEntity peliculaSaved = this.peliculaRepository.save(entity.get());
    return peliculaMapper.peliculaEntity2DTO(peliculaSaved, false);
  }
}
