package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

  PersonajeService personajeService;

  @Autowired
  public PersonajeController(PersonajeService personajeService) {
    this.personajeService = personajeService;
  }

  @PostMapping
  public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje) {
    PersonajeDTO personajeGuardado = personajeService.save(personaje);
    return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
  }
}
