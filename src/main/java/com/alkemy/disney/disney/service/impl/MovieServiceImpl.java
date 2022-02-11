package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieFilterDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.Movie;
import com.alkemy.disney.disney.entity.Character;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.MovieService;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;
  private final CharacterService characterService;
  private final MovieMapper movieMapper;
  private final CharacterMapper characterMapper;

  @Autowired
  public MovieServiceImpl(MovieMapper movieMapper, MovieRepository movieRepository, CharacterService characterService, CharacterMapper characterMapper) {
    this.movieMapper = movieMapper;
    this.movieRepository = movieRepository;
    this.characterService = characterService;
    this.characterMapper = characterMapper;
  }

  @Override
  public MovieDTO save(MovieDTO dto) {
    Movie movie = movieMapper.movieDTO2Entity(dto);
    Set<Character> characters = characterMapper.characterDTOList2Entity(dto.getCharacters());
    movie.setCharacters(characters);
    Movie entitySave = this.movieRepository.save(movie);
    return getMovieDetailsDTO(entitySave);
  }

  @Override
  public List<MovieBasicDTO> getAll(String name, Long idGenre, String order) {
    MovieFilterDTO movieFilterDTO = new MovieFilterDTO(name, idGenre, order);
    Specification<Movie> spec = MovieSpecification.getByFilters(movieFilterDTO);
    List<Movie> all = movieRepository.findAll(spec);
    return movieMapper.movieEntityList2BasicDTOList(all);
  }

  @Override
  public MovieDTO getDetailsById(Long id) {
    return this.movieRepository.findById(id)
        .map(this::getMovieDetailsDTO)
        .orElseThrow(() -> new ParamNotFound("Character id not valid"));
  }

  @Override
  public void delete(Long id) {
    this.movieRepository.deleteById(id);
  }

  @Override
  public void addCharacter(Long id, Long idCharacter) {
    Movie entity = movieRepository.getById(id);
    Character character = characterService.getById(idCharacter);
    entity.addCharacter(character);
    movieRepository.save(entity);
  }

  @Override
  public void removeCharacter(Long id, Long idCharacter) {
    Movie entity = movieRepository.getById(id);
    Character character = characterService.getById(idCharacter);
    entity.removeCharacter(character);
    movieRepository.save(entity);
  }

  @Override
  public MovieDTO update(Long id, MovieDTO movie) {
    Optional<Movie> entity = movieRepository.findById(id);
    if (entity.isEmpty()) {
      throw new ParamNotFound("Character id not valid");
    }
    this.movieMapper.movieEntityRefreshValues(entity.get(), movie);
    Movie movieSaved = this.movieRepository.save(entity.get());
    return movieMapper.movieEntity2DTO(movieSaved);
  }

  private MovieDTO getMovieDetailsDTO(Movie movie) {
    MovieDTO movieDTO = this.movieMapper.movieEntity2DTO(movie);
    List<CharacterDTO> characterDTOS = this.characterMapper.characterEntitySet2DTOList(movie.getCharacters());
    movieDTO.setCharacters(characterDTOS);
    return movieDTO;
  }
}
