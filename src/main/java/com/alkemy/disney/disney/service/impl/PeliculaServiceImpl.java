package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFilterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.entity.Personaje;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
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
  private final MovieMapper movieMapper;
  private final CharacterMapper characterMapper;

  @Autowired
  public PeliculaServiceImpl(MovieMapper movieMapper, PeliculaRepository peliculaRepository, PersonajeService personajeService, CharacterMapper characterMapper) {
    this.movieMapper = movieMapper;
    this.peliculaRepository = peliculaRepository;
    this.personajeService = personajeService;
    this.characterMapper = characterMapper;
  }

  @Override
  public MovieDTO save(MovieDTO dto) {
    Movie movie = movieMapper.peliculaDTO2Entity(dto);
    Set<Personaje> personajes = characterMapper.personajeDTOList2Entity(dto.getPersonajes());
    movie.setPersonajes(personajes);
    Movie entitySave = this.peliculaRepository.save(movie);
    return getPeliculaDetailsDTO(entitySave);
  }

  @Override
  public List<MovieBasicDTO> getAll(String name, Long idGenre, String order) {
    MovieFilterDTO movieFilterDTO = new MovieFilterDTO(name, idGenre, order);
    Specification<Movie> spec = MovieSpecification.getByFilters(movieFilterDTO);
    List<Movie> all = peliculaRepository.findAll(spec);
    return movieMapper.pelicualEntityList2BasicDTOList(all);
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
    Movie entity = peliculaRepository.getById(id);
    Personaje personaje = personajeService.getById(idPersonaje);
    entity.addPersonaje(personaje);
    peliculaRepository.save(entity);
  }

  @Override
  public void removePersonaje(Long id, Long idPersonaje) {
    Movie entity = peliculaRepository.getById(id);
    Personaje personaje = personajeService.getById(idPersonaje);
    entity.removePersonaje(personaje);
    peliculaRepository.save(entity);
  }

  @Override
  public MovieDTO update(Long id, MovieDTO movieDTO) {
    Optional<Movie> entity = peliculaRepository.findById(id);
    if (entity.isEmpty()) {
      throw new ParamNotFound("Id de presonaje no valido");
    }
    this.movieMapper.peliculaEntityRefreshValues(entity.get(), movieDTO);
    Movie movieSaved = this.peliculaRepository.save(entity.get());
    return movieMapper.peliculaEntity2DTO(movieSaved);
  }

  private MovieDTO getPeliculaDetailsDTO(Movie movie) {
    MovieDTO movieDTO = this.movieMapper.peliculaEntity2DTO(movie);
    List<CharacterDTO> characterDTOS = this.characterMapper.personajeEntitySet2DTOList(movie.getPersonajes());
    movieDTO.setPersonajes(characterDTOS);
    return movieDTO;
  }
}
