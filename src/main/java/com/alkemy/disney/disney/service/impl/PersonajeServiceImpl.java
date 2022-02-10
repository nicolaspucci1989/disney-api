package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeFilterDTO;
import com.alkemy.disney.disney.entity.Personaje;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.specifications.PersonajeSpecification;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

  private final PersonajeRepository personajeRepository;
  private final PersonajeMapper personajeMapper;
  private final PeliculaMapper peliculaMapper;

  @Autowired
  public PersonajeServiceImpl(PersonajeRepository personajeRepository, PersonajeMapper personajeMapper, PeliculaMapper peliculaMapper) {
    this.personajeRepository = personajeRepository;
    this.personajeMapper = personajeMapper;
    this.peliculaMapper = peliculaMapper;
  }

  @Override
  public PersonajeDTO save(PersonajeDTO dto) {
    Personaje entity = personajeMapper.personajeDTO2Entity(dto);
    Personaje save = personajeRepository.save(entity);
    return personajeMapper.personajeEntity2DTO(save);
  }

  @Override
  public List<PersonajeBasicDTO> getAll(String name, Integer age, List<Long> idMovies) {
    PersonajeFilterDTO personajeFilterDTO = new PersonajeFilterDTO(name, age, idMovies);
    Specification<Personaje> spec = PersonajeSpecification.getByFilters(personajeFilterDTO);
    List<Personaje> entities = this.personajeRepository.findAll(spec);
    return personajeMapper.personajeEntityList2BasicDTOList(entities);
  }

  @Override
  public PersonajeDTO getDetailsById(Long id) {
    return this.personajeRepository.findById(id)
        .map(this::getPersonajePersonajeDTOFunction)
        .orElseThrow(() -> new ParamNotFound("no se encontro"));
  }

  @Override
  public PersonajeDTO update(Long id, PersonajeDTO personajeDTO) {
    Optional<Personaje> entity = personajeRepository.findById(id);
    if (entity.isEmpty()) {
      throw new ParamNotFound("Id de presonaje no valido");
    }
    this.personajeMapper.personajeEntityRefreshValues(entity.get(), personajeDTO);
    Personaje personajeSaved = this.personajeRepository.save(entity.get());
    return personajeMapper.personajeEntity2DTO(personajeSaved);
  }

  @Override
  public void delete(Long id) {
    this.personajeRepository.deleteById(id);
  }

  @Override
  public Personaje getById(Long idPersonaje) {
    return this.personajeRepository.getById(idPersonaje);
  }

  private PersonajeDTO getPersonajePersonajeDTOFunction(Personaje personajeEntity) {
    PersonajeDTO personajeDTO = this.personajeMapper.personajeEntity2DTO(personajeEntity);
    List<PeliculaDTO> peliculaDTOS = peliculaMapper.peliculaEntityList2DTOList(personajeEntity.getPeliculas());
    personajeDTO.setPeliculas(peliculaDTOS);
    return personajeDTO;
  }

}
