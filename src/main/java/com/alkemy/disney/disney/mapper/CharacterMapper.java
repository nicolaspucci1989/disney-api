package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Character;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CharacterMapper {

  public Character characterDTO2Entity(CharacterDTO dto) {
    Character entity = new Character();
    entity.setImage(dto.getImage());
    entity.setName(dto.getName());
    entity.setAge(dto.getAge());
    entity.setWeight(dto.getWeight());
    entity.setHistory(dto.getHistory());
    return entity;
  }

  public CharacterDTO characterEntity2DTO(Character entity) {
    CharacterDTO dto = new CharacterDTO();
    dto.setId(entity.getId());
    dto.setImage(entity.getImage());
    dto.setName(entity.getName());
    dto.setAge(entity.getAge());
    dto.setWeight(entity.getWeight());
    dto.setHistory(entity.getHistory());
    return dto;
  }

  public Set<Character> characterDTOList2Entity(List<CharacterDTO> dtos) {
    return dtos.stream()
        .map(this::characterDTO2Entity).collect(Collectors.toSet());
  }

  public List<CharacterDTO> characterEntitySet2DTOList(Collection<Character> entities) {
    return entities.stream()
        .map(this::characterEntity2DTO)
        .collect(Collectors.toList());
  }

  public List<CharacterBasicDTO> characterEntityList2BasicDTOList(Collection<Character> entities) {
    return entities.stream()
        .map(e -> CharacterBasicDTO
            .builder()
            .image(e.getImage())
            .name(e.getName())
            .build()
        )
        .collect(Collectors.toList());
  }

  public void characterEntityRefreshValues(Character entity, CharacterDTO characterDTO) {
    entity.setHistory(characterDTO.getHistory());
    entity.setName(characterDTO.getName());
    entity.setImage(characterDTO.getImage());
    entity.setWeight(characterDTO.getWeight());
    entity.setAge(characterDTO.getAge());
  }
}
