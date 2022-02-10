package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.Personaje;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersonajeMapper {

  public Personaje personajeDTO2Entity(PersonajeDTO dto) {
    Personaje entity = new Personaje();
    entity.setImagen(dto.getImagen());
    entity.setNombre(dto.getNombre());
    entity.setEdad(dto.getEdad());
    entity.setPeso(dto.getPeso());
    entity.setHistoria(dto.getHistoria());
    return entity;
  }

  public PersonajeDTO personajeEntity2DTO(Personaje entity) {
    PersonajeDTO dto = new PersonajeDTO();
    dto.setId(entity.getId());
    dto.setImagen(entity.getImagen());
    dto.setNombre(entity.getNombre());
    dto.setEdad(entity.getEdad());
    dto.setPeso(entity.getPeso());
    dto.setHistoria(entity.getHistoria());
    return dto;
  }

  public Set<Personaje> personajeDTOList2Entity(List<PersonajeDTO> dtos) {
    return dtos.stream()
        .map(this::personajeDTO2Entity).collect(Collectors.toSet());
  }

  public List<PersonajeDTO> personajeEntitySet2DTOList(Collection<Personaje> entities) {
    return entities.stream()
        .map(this::personajeEntity2DTO)
        .collect(Collectors.toList());
  }

  public List<CharacterBasicDTO> personajeEntityList2BasicDTOList(Collection<Personaje> entities) {
    return entities.stream()
        .map(e -> CharacterBasicDTO
            .builder()
            .imagen(e.getImagen())
            .nombre(e.getNombre())
            .build()
        )
        .collect(Collectors.toList());
  }

  public void personajeEntityRefreshValues(Personaje entity, PersonajeDTO personajeDTO) {
    entity.setHistoria(personajeDTO.getHistoria());
    entity.setNombre(personajeDTO.getNombre());
    entity.setImagen(personajeDTO.getImagen());
    entity.setPeso(personajeDTO.getPeso());
    entity.setEdad(personajeDTO.getEdad());
  }
}
