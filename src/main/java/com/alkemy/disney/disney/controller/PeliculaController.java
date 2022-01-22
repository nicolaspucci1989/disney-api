package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<List<PeliculaBasicDTO>> getAll() {
    List<PeliculaBasicDTO> dtos = peliculaService.getAll();
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<PeliculaDTO> getDetailsById(@PathVariable Long id) {
    PeliculaDTO dto = peliculaService.getDetailsById(id);
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula) {
    PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
    return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
  }
}
