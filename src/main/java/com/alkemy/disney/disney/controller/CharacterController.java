package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

  PersonajeService personajeService;

  @Autowired
  public CharacterController(PersonajeService personajeService) {
    this.personajeService = personajeService;
  }

  @GetMapping
  public ResponseEntity<List<PersonajeBasicDTO>> getAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Integer age,
      @RequestParam(required = false) List<Long> movies
  ) {
    List<PersonajeBasicDTO> personajes = this.personajeService.getAll(name, age, movies);
    return ResponseEntity.ok(personajes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id) {
    PersonajeDTO personaje = personajeService.getDetailsById(id);
    return ResponseEntity.ok(personaje);
  }

  @PostMapping
  public ResponseEntity<PersonajeDTO> save(@Valid @RequestBody PersonajeDTO personaje) {
    PersonajeDTO personajeGuardado = personajeService.save(personaje);
    return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
  }

  @PutMapping("{id}")
  public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @Valid @RequestBody PersonajeDTO personaje) {
    PersonajeDTO res = this.personajeService.update(id, personaje);
    return ResponseEntity.ok().body(res);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.personajeService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
