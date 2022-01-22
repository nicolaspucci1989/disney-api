package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServiceImpl implements PersonajeService {

  private PersonajeRepository personajeRepository;
  private PersonajeMapper personajeMapper;

  @Autowired
  public PersonajeServiceImpl(PersonajeRepository personajeRepository, PersonajeMapper personajeMapper) {
    this.personajeRepository = personajeRepository;
    this.personajeMapper = personajeMapper;
  }

  public PersonajeDTO save(PersonajeDTO dto) {
    PersonajeEntity entity = personajeMapper.personajeDTO2Entity(dto);
    PersonajeEntity save = personajeRepository.save(entity);
    return personajeMapper.personajeEntity2DTO(save, false);
  }

  @Override
  public List<PersonajeBasicDTO> getAll() {
    List<PersonajeEntity> entities = this.personajeRepository.findAll();
    return personajeMapper.personajeEntityList2BasicDTOList(entities);
  }

  @Override
  public PersonajeDTO getDetailsById(Long id) {
    // TODO: agregar excepcion posta
    return this.personajeRepository.findById(id)
        .map(e -> this.personajeMapper.personajeEntity2DTO(e, true))
        .orElseThrow(() -> new RuntimeException("no se encontro"));
  }
}
