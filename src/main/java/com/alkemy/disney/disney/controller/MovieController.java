package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieBasicDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

  private final MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity<List<MovieBasicDTO>> getAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Long idGenre,
      @RequestParam(defaultValue = "ASC") String order
  ) {
    List<MovieBasicDTO> DTOs = movieService.getAll(name, idGenre, order);
    return ResponseEntity.ok(DTOs);
  }

  @GetMapping("{id}")
  public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id) {
    MovieDTO dto = movieService.getDetailsById(id);
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movie) {
    MovieDTO movieSave = movieService.save(movie);
    return ResponseEntity.status(HttpStatus.CREATED).body(movieSave);
  }

  @PutMapping("{id}")
  public ResponseEntity<MovieDTO> update(@PathVariable Long id, @Valid @RequestBody MovieDTO movie) {
    MovieDTO res = this.movieService.update(id, movie);
    return ResponseEntity.ok().body(res);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.movieService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("{id}/character/{idCharacter}")
  public ResponseEntity<Void> addCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
    this.movieService.addCharacter(id, idCharacter);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("{id}/character/{idCharacter}")
  public ResponseEntity<Void> removeCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
    this.movieService.removeCharacter(id, idCharacter);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
