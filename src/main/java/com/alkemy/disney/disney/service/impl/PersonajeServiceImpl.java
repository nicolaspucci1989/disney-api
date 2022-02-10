package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.disney.entity.Personaje;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

  private final PersonajeRepository personajeRepository;
  private final CharacterMapper characterMapper;
  private final MovieMapper movieMapper;

  @Autowired
  public PersonajeServiceImpl(PersonajeRepository personajeRepository, CharacterMapper characterMapper, MovieMapper movieMapper) {
    this.personajeRepository = personajeRepository;
    this.characterMapper = characterMapper;
    this.movieMapper = movieMapper;
  }

  @Override
  public CharacterDTO save(CharacterDTO dto) {
    Personaje entity = characterMapper.personajeDTO2Entity(dto);
    Personaje save = personajeRepository.save(entity);
    return characterMapper.personajeEntity2DTO(save);
  }

  @Override
  public List<CharacterBasicDTO> getAll(String name, Integer age, List<Long> idMovies) {
    CharacterFilterDTO characterFilterDTO = new CharacterFilterDTO(name, age, idMovies);
    Specification<Personaje> spec = CharacterSpecification.getByFilters(characterFilterDTO);
    List<Personaje> entities = this.personajeRepository.findAll(spec);
    return characterMapper.personajeEntityList2BasicDTOList(entities);
  }

  @Override
  public CharacterDTO getDetailsById(Long id) {
    return this.personajeRepository.findById(id)
        .map(this::getPersonajePersonajeDTOFunction)
        .orElseThrow(() -> new ParamNotFound("no se encontro"));
  }

  @Override
  public CharacterDTO update(Long id, CharacterDTO characterDTO) {
    Optional<Personaje> entity = personajeRepository.findById(id);
    if (entity.isEmpty()) {
      throw new ParamNotFound("Id de presonaje no valido");
    }
    this.characterMapper.personajeEntityRefreshValues(entity.get(), characterDTO);
    Personaje personajeSaved = this.personajeRepository.save(entity.get());
    return characterMapper.personajeEntity2DTO(personajeSaved);
  }

  @Override
  public void delete(Long id) {
    this.personajeRepository.deleteById(id);
  }

  @Override
  public Personaje getById(Long idPersonaje) {
    return this.personajeRepository.getById(idPersonaje);
  }

  private CharacterDTO getPersonajePersonajeDTOFunction(Personaje personajeEntity) {
    CharacterDTO characterDTO = this.characterMapper.personajeEntity2DTO(personajeEntity);
    List<MovieDTO> movieDTOS = movieMapper.peliculaEntityList2DTOList(personajeEntity.getMovies());
    characterDTO.setPeliculas(movieDTOS);
    return characterDTO;
  }

}
