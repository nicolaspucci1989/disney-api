package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class PeliculaController {

  private PeliculaService peliculaService;

  @Autowired
  public PeliculaController(PeliculaService peliculaService) {
    this.peliculaService = peliculaService;
  }

  @PostMapping
  public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula) {
    PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
    return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
  }
}
