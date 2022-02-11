package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.Genre;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

  private final GenreMapper genreMapper;
  private final GenreRepository genreRepository;

  @Autowired
  public GenreServiceImpl(GenreMapper genreMapper, GenreRepository genreRepository) {
    this.genreMapper = genreMapper;
    this.genreRepository = genreRepository;
  }

  public GenreDTO save(GenreDTO dto) {
    Genre genre = genreMapper.genreDTO2Entity(dto);
    Genre entitySave = this.genreRepository.save(genre);
    return this.genreMapper.genreEntity2DTO(entitySave);
  }
}
