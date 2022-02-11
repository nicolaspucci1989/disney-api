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
    List<MovieBasicDTO> dtos = movieService.getAll(name, idGenre, order);
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<MovieDTO> getDetailsById(@PathVariable Long id) {
    MovieDTO dto = movieService.getDetailsById(id);
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO pelicula) {
    MovieDTO peliculaGuardada = movieService.save(pelicula);
    return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
  }

  @PutMapping("{id}")
  public ResponseEntity<MovieDTO> update(@PathVariable Long id, @Valid @RequestBody MovieDTO pelicula) {
    MovieDTO res = this.movieService.update(id, pelicula);
    return ResponseEntity.ok().body(res);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.movieService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("{id}/character/{idPersonaje}")
  public ResponseEntity<Void> addPersonaje(@PathVariable Long id, @PathVariable Long idPersonaje) {
    this.movieService.addCharacter(id, idPersonaje);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("{id}/character/{idPersonaje}")
  public ResponseEntity<Void> removePersonaje(@PathVariable Long id, @PathVariable Long idPersonaje) {
    this.movieService.removeCharacter(id, idPersonaje);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
