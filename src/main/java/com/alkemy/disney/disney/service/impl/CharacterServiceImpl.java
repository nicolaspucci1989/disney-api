package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

  private final CharacterRepository characterRepository;
  private final CharacterMapper characterMapper;
  private final MovieMapper movieMapper;

  @Autowired
  public CharacterServiceImpl(CharacterRepository characterRepository, CharacterMapper characterMapper, MovieMapper movieMapper) {
    this.characterRepository = characterRepository;
    this.characterMapper = characterMapper;
    this.movieMapper = movieMapper;
  }

  @Override
  public CharacterDTO save(CharacterDTO dto) {
    Character entity = characterMapper.characterDTO2Entity(dto);
    Character save = characterRepository.save(entity);
    return characterMapper.characterEntity2DTO(save);
  }

  @Override
  public List<CharacterBasicDTO> getAll(String name, Integer age, List<Long> idMovies) {
    CharacterFilterDTO characterFilterDTO = new CharacterFilterDTO(name, age, idMovies);
    Specification<Character> spec = CharacterSpecification.getByFilters(characterFilterDTO);
    List<Character> entities = this.characterRepository.findAll(spec);
    return characterMapper.characterEntityList2BasicDTOList(entities);
  }

  @Override
  public CharacterDTO getDetailsById(Long id) {
    return this.characterRepository.findById(id)
        .map(this::getCharacterDetailDTO)
        .orElseThrow(() -> new ParamNotFound("Character not found"));
  }

  @Override
  public CharacterDTO update(Long id, CharacterDTO character) {
    Optional<Character> entity = characterRepository.findById(id);
    if (entity.isEmpty()) {
      throw new ParamNotFound("Character id is not valid");
    }
    this.characterMapper.characterEntityRefreshValues(entity.get(), character);
    Character characterSaved = this.characterRepository.save(entity.get());
    return characterMapper.characterEntity2DTO(characterSaved);
  }

  @Override
  public void delete(Long id) {
    this.characterRepository.deleteById(id);
  }

  @Override
  public Character getById(Long idCharacter) {
    return this.characterRepository.getById(idCharacter);
  }

  private CharacterDTO getCharacterDetailDTO(Character character) {
    CharacterDTO characterDTO = this.characterMapper.characterEntity2DTO(character);
    List<MovieDTO> movieDTOS = movieMapper.movieEntityList2DTOList(character.getMovies());
    characterDTO.setMovies(movieDTOS);
    return characterDTO;
  }

}
