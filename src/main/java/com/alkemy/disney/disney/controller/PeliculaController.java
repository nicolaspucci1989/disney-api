package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.service.PeliculaService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movies")
public class PeliculaController {

  private final PeliculaService peliculaService;

  @Autowired
  public PeliculaController(PeliculaService peliculaService) {
    this.peliculaService = peliculaService;
  }

  @GetMapping
  public ResponseEntity<List<PeliculaBasicDTO>> getAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Long idGenre,
      @RequestParam(defaultValue = "ASC") String order
  ) {
    List<PeliculaBasicDTO> dtos = peliculaService.getAll(name, idGenre, order);
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<PeliculaDTO> getDetailsById(@PathVariable Long id) {
    PeliculaDTO dto = peliculaService.getDetailsById(id);
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<PeliculaDTO> save(@Valid @RequestBody PeliculaDTO pelicula) {
    PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
    return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
  }

  @PutMapping("{id}")
  public ResponseEntity<PeliculaDTO> update(@PathVariable Long id, @RequestBody PeliculaDTO pelicula) {
    PeliculaDTO res = this.peliculaService.update(id, pelicula);
    return ResponseEntity.ok().body(res);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.peliculaService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping("{id}/character/{idPersonaje}")
  public ResponseEntity<Void> addPersonaje(@PathVariable Long id, @PathVariable Long idPersonaje) {
    this.peliculaService.addPersonaje(id, idPersonaje);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("{id}/character/{idPersonaje}")
  public ResponseEntity<Void> removePersonaje(@PathVariable Long id, @PathVariable Long idPersonaje) {
    this.peliculaService.removePersonaje(id, idPersonaje);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
