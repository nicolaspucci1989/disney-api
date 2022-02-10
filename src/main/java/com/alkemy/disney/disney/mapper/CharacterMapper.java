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
    entity.setImagen(dto.getImagen());
    entity.setNombre(dto.getNombre());
    entity.setEdad(dto.getEdad());
    entity.setPeso(dto.getPeso());
    entity.setHistoria(dto.getHistoria());
    return entity;
  }

  public CharacterDTO personajeEntity2DTO(Character entity) {
    CharacterDTO dto = new CharacterDTO();
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setNombre(entity.getNombre());
    dto.setEdad(entity.getEdad());
    dto.setPeso(entity.getPeso());
    dto.setHistoria(entity.getHistoria());
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
            .imagen(e.getImagen())
            .nombre(e.getNombre())
            .build()
        )
        .collect(Collectors.toList());
  }

  public void personajeEntityRefreshValues(Character entity, CharacterDTO characterDTO) {
    entity.setHistoria(characterDTO.getHistoria());
    entity.setNombre(characterDTO.getNombre());
    entity.setImagen(characterDTO.getImagen());
    entity.setPeso(characterDTO.getPeso());
    entity.setEdad(characterDTO.getEdad());
  }
}
