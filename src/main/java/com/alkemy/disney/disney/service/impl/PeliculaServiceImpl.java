package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFilterDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.Pelicula;
import com.alkemy.disney.disney.entity.Personaje;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.specifications.PeliculaSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeliculaServiceImpl implements PeliculaService {

  private final PeliculaRepository peliculaRepository;
  private final PersonajeService personajeService;
  private final PeliculaMapper peliculaMapper;
  private final PersonajeMapper personajeMapper;

  @Autowired
  public PeliculaServiceImpl(PeliculaMapper peliculaMapper, PeliculaRepository peliculaRepository, PersonajeService personajeService, PersonajeMapper personajeMapper) {
    this.peliculaMapper = peliculaMapper;
    this.peliculaRepository = peliculaRepository;
    this.personajeService = personajeService;
    this.personajeMapper = personajeMapper;
  }

  @Override
  public MovieDTO save(MovieDTO dto) {
    Pelicula pelicula = peliculaMapper.peliculaDTO2Entity(dto);
    Set<Personaje> personajes = personajeMapper.personajeDTOList2Entity(dto.getPersonajes());
    pelicula.setPersonajes(personajes);
    Pelicula entitySave = this.peliculaRepository.save(pelicula);
    return getPeliculaDetailsDTO(entitySave);
  }

  @Override
  public List<MovieBasicDTO> getAll(String name, Long idGenre, String order) {
    MovieFilterDTO movieFilterDTO = new MovieFilterDTO(name, idGenre, order);
    Specification<Pelicula> spec = PeliculaSpecification.getByFilters(movieFilterDTO);
    List<Pelicula> all = peliculaRepository.findAll(spec);
    return peliculaMapper.pelicualEntityList2BasicDTOList(all);
  }

  @Override
  public MovieDTO getDetailsById(Long id) {
    return this.peliculaRepository.findById(id)
        .map(this::getPeliculaDetailsDTO)
        .orElseThrow(() -> new ParamNotFound("Id de pelicula no valido"));
  }

  @Override
  public void delete(Long id) {
    this.peliculaRepository.deleteById(id);
  }

  @Override
  public void addPersonaje(Long id, Long idPersonaje) {
    Pelicula entity = peliculaRepository.getById(id);
    Personaje personaje = personajeService.getById(idPersonaje);
    entity.addPersonaje(personaje);
    peliculaRepository.save(entity);
  }

  @Override
  public void removePersonaje(Long id, Long idPersonaje) {
    Pelicula entity = peliculaRepository.getById(id);
    Personaje personaje = personajeService.getById(idPersonaje);
    entity.removePersonaje(personaje);
    peliculaRepository.save(entity);
  }

  @Override
  public MovieDTO update(Long id, MovieDTO movieDTO) {
    Optional<Pelicula> entity = peliculaRepository.findById(id);
    if (entity.isEmpty()) {
      throw new ParamNotFound("Id de presonaje no valido");
    }
    this.peliculaMapper.peliculaEntityRefreshValues(entity.get(), movieDTO);
    Pelicula peliculaSaved = this.peliculaRepository.save(entity.get());
    return peliculaMapper.peliculaEntity2DTO(peliculaSaved);
  }

  private MovieDTO getPeliculaDetailsDTO(Pelicula pelicula) {
    MovieDTO movieDTO = this.peliculaMapper.peliculaEntity2DTO(pelicula);
    List<PersonajeDTO> personajeDTOS = this.personajeMapper.personajeEntitySet2DTOList(pelicula.getPersonajes());
    movieDTO.setPersonajes(personajeDTOS);
    return movieDTO;
  }
}
