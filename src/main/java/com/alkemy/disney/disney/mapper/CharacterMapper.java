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

  public Character personajeDTO2Entity(CharacterDTO dto) {
    Character entity = new Character();
    entity.setImage(dto.getImage());
    entity.setName(dto.getName());
    entity.setAge(dto.getAge());
    entity.setPeso(dto.getPeso());
    entity.setHistory(dto.getHistory());
    return entity;
  }

  public CharacterDTO personajeEntity2DTO(Character entity) {
    CharacterDTO dto = new CharacterDTO();
    dto.setId(entity.getId());
    dto.setImage(entity.getImage());
    dto.setName(entity.getName());
    dto.setAge(entity.getAge());
    dto.setPeso(entity.getPeso());
    dto.setHistory(entity.getHistory());
    return dto;
  }

  public Set<Character> personajeDTOList2Entity(List<CharacterDTO> dtos) {
    return dtos.stream()
        .map(this::personajeDTO2Entity).collect(Collectors.toSet());
  }

  public List<CharacterDTO> personajeEntitySet2DTOList(Collection<Character> entities) {
    return entities.stream()
        .map(this::personajeEntity2DTO)
        .collect(Collectors.toList());
  }

  public List<CharacterBasicDTO> personajeEntityList2BasicDTOList(Collection<Character> entities) {
    return entities.stream()
        .map(e -> CharacterBasicDTO
            .builder()
            .image(e.getImage())
            .name(e.getName())
            .build()
        )
        .collect(Collectors.toList());
  }

  public void personajeEntityRefreshValues(Character entity, CharacterDTO characterDTO) {
    entity.setHistory(characterDTO.getHistory());
    entity.setName(characterDTO.getName());
    entity.setImage(characterDTO.getImage());
    entity.setPeso(characterDTO.getPeso());
    entity.setAge(characterDTO.getAge());
  }
}
