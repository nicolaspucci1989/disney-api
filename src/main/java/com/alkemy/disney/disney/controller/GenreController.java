package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/genre")
public class GenreController {

  private final GeneroService generoService;

  @Autowired
  public GenreController(GeneroService generoService) {
    this.generoService = generoService;
  }

  @PostMapping
  public ResponseEntity<GenreDTO> save(@Valid @RequestBody GenreDTO genero) {
    GenreDTO generoGuardado = generoService.save(genero);
    return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
  }
}
